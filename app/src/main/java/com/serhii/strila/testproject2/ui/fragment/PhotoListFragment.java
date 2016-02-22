package com.serhii.strila.testproject2.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.serhii.strila.testproject2.R;
import com.serhii.strila.testproject2.model.PhotoModel;
import com.serhii.strila.testproject2.model.PhotosEvent;
import com.serhii.strila.testproject2.model.pojo.Photo;
import com.serhii.strila.testproject2.ui.adapter.PhotoAdapter;
import com.serhii.strila.testproject2.ui.listener.EndlessListener;
import com.serhii.strila.testproject2.ui.listener.OnItemClickListener;
import com.trello.rxlifecycle.FragmentEvent;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;

public class PhotoListFragment extends BaseFragment implements OnItemClickListener {

    @Inject
    PhotoModel mPhotoModel;
    @Bind(R.id.rv_photos)
    RecyclerView mRvPhotos;

    private PhotoAdapter mAdapter;

    public static PhotoListFragment newInstance() {
        PhotoListFragment fragment = new PhotoListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        initList();
        subEvents();
        loadPhotos(1);
        return rootView;
    }

    @Override
    public void onItemClick(int position) {
        Snackbar.make(mRvPhotos, mAdapter.getItem(position).getTitle(), Snackbar.LENGTH_LONG)
                .show();
    }

    private void subEvents() {
        mPhotoModel.subPhotos()
                .compose(bindUntilEvent(FragmentEvent.DESTROY_VIEW))
                .doOnNext(photosEvent -> {
                    if (photosEvent.isReload()) {
                        mAdapter.clear();
                    }
                })
                .doOnNext(ph -> mAdapter.setFooterVisibility(false))
                .doOnError(ph -> mAdapter.setFooterVisibility(false))
                .map(PhotosEvent::getPhotos)
                .subscribe(mAdapter::addItems, Throwable::printStackTrace);
    }

    private void initList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mAdapter = new PhotoAdapter();
        mAdapter.addItems(mPhotoModel.getPhotos());
        mAdapter.setOnClickListener(this);
        mRvPhotos.setHasFixedSize(true);
        mRvPhotos.setLayoutManager(layoutManager);
        mRvPhotos.setAdapter(mAdapter);
        mRvPhotos.addOnScrollListener(new EndlessListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadPhotos(currentPage);
            }
        });
    }

    private void loadPhotos(int page) {
        mAdapter.setFooterVisibility(true);
        mPhotoModel.getNaturePhoto(page);
    }
}