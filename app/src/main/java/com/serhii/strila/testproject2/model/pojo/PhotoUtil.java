package com.serhii.strila.testproject2.model.pojo;

/**
 * Created by Serhii Strila on 2/18/16
 */
public class PhotoUtil {

    public static String getImageUrl(Photo photo) {
        StringBuilder sb = new StringBuilder();
        sb.append("http://farm");
        sb.append(photo.getFarm());
        sb.append(".staticflickr.com/");
        sb.append(photo.getServer());
        sb.append("/");
        sb.append(photo.getId());
        sb.append("_");
        sb.append(photo.getSecret());
        sb.append(".jpg");
        return sb.toString();
    }

}
