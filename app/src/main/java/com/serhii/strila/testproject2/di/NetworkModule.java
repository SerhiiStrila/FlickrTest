package com.serhii.strila.testproject2.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.serhii.strila.testproject2.BuildConfig;
import com.serhii.strila.testproject2.model.Api;
import com.serhii.strila.testproject2.model.PhotoModel;

import dagger.Module;
import dagger.Provides;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Serhii Strila on 2/17/16
 */
@Module
public class NetworkModule {

    @Provides
    @PerApp
    Api provideApi(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(Api.class);
    }

    @Provides
    @PerApp
    Gson provideGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Provides
    @PerApp
    PhotoModel providePhotos(Api api) {
        return new PhotoModel(api);
    }

}
