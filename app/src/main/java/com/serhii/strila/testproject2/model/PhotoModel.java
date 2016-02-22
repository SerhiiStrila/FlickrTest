package com.serhii.strila.testproject2.model;

import com.serhii.strila.testproject2.model.pojo.Photo;
import com.serhii.strila.testproject2.model.pojo.PhotoSource;

import java.io.IOException;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.subjects.PublishSubject;

public class PhotoModel {

    private static final String SORT_INTERESTINGNESS_DESC = "interestingness-desc";
    private static final String SORT_DATE_POSTED_DESC = "date-posted-desc";
    private static final String NATURE_SEARCH_TEXT = "nature";
    private static final int PER_PAGE = 20;

    private Api mApi;
    private PublishSubject<PhotosEvent> mPhotos;
    private Call<PhotoSource> mQuery;

    public PhotoModel(Api api) {
        mApi = api;
        mPhotos = PublishSubject.create();
    }

    public void getNaturePhoto(int page) {
        mQuery = mApi.photosSearch(NATURE_SEARCH_TEXT, page, PER_PAGE, SORT_INTERESTINGNESS_DESC);
        mQuery.enqueue(new Callback<PhotoSource>() {
            @Override
            public void onResponse(Response<PhotoSource> response) {
                if (!response.isSuccess()) {
                    try {
                        mPhotos.onError(new Throwable(response.errorBody().string()));
                    } catch (IOException e) {
                        mPhotos.onError(e);
                    }
                    return;
                }
                List<Photo> photos = response.body().getPhotos();
                saveLastSearch(photos);  // save to bd
                mPhotos.onNext(new PhotosEvent(photos, page == 1));  // send to ui
            }

            @Override
            public void onFailure(Throwable t) {
                mPhotos.onError(t);
            }
        });
    }

    public Observable<PhotosEvent> subPhotos() {
        return mPhotos.doOnUnsubscribe(() -> {
                    if (mQuery != null && !mQuery.isExecuted())
                        mQuery.cancel();
                });
    }

    private void saveLastSearch(List<Photo> photos) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(Photo.class).findAll().clear();
        realm.copyToRealmOrUpdate(photos);
        realm.commitTransaction();
        realm.close();
    }

    public List<Photo> getPhotos() {
        return Realm.getDefaultInstance().where(Photo.class).findAll();
    }
}
