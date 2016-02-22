package com.serhii.strila.testproject2.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotoSource {

    @SerializedName("photos")
    @Expose
    private Photos photos;
    @SerializedName("stat")
    @Expose
    private String stat;

    public List<Photo> getPhotos() {
        return photos.photo;
    }

    public class Photos {

        @SerializedName("page")
        @Expose
        private int page;
        @SerializedName("pages")
        @Expose
        private int pages;
        @SerializedName("perpage")
        @Expose
        private int perpage;
        @SerializedName("total")
        @Expose
        private String total;
        @SerializedName("photo")
        @Expose
        private List<Photo> photo;
    }

}
