package com.serhii.strila.testproject2.model;

import com.serhii.strila.testproject2.model.pojo.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serhii Strila on 2/22/16
 */
public class PhotosEvent {

    private List<Photo> mPhotos = new ArrayList<>();
    private boolean reload;

    public PhotosEvent(List<Photo> photos) {
        mPhotos = photos;
    }

    public PhotosEvent(List<Photo> photos, boolean reload) {
        mPhotos = photos;
        this.reload = reload;
    }

    public List<Photo> getPhotos() {
        return mPhotos;
    }

    public boolean isReload() {
        return reload;
    }
}
