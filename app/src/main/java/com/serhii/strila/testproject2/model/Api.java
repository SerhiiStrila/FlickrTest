package com.serhii.strila.testproject2.model;

import com.serhii.strila.testproject2.model.pojo.PhotoSource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Serhii Strila on 2/17/16
 */
public interface Api {

    String FLICKR_API_KEY = "eba2bf2220e78e40a66c3083dc1a3069";

    @GET("/services/rest?method=flickr.photos.search&api_key=" + FLICKR_API_KEY
            + "&format=json&nojsoncallback=1")
    Call<PhotoSource> photosSearch(@Query("text") String text,
            @Query("page") int page,
            @Query("per_page") int perpage,
            @Query("sort") String sort);

}
