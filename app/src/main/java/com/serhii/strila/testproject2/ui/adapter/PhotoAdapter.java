package com.serhii.strila.testproject2.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.serhii.strila.testproject2.R;
import com.serhii.strila.testproject2.model.pojo.Photo;
import com.serhii.strila.testproject2.ui.adapter.viewholder.FooterProgressViewHolder;
import com.serhii.strila.testproject2.ui.adapter.viewholder.PhotoViewHolder;
import com.serhii.strila.testproject2.ui.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serhii Strila on 2/17/16
 */
public class PhotoAdapter extends UltimateAdapter<PhotoViewHolder> implements
        UltimateAdapter.FooterInterface {

    private List<Photo> mPhotos;
    @Nullable
    private OnItemClickListener mListener;

    public PhotoAdapter() {
        mPhotos = new ArrayList<>();
    }

    public void addItems(List<Photo> photo) {
        mPhotos.addAll(photo);
    }

    public Photo getItem(int position) {
        return mPhotos.get(position);
    }

    public void clear() {
        mPhotos.clear();
    }

    public void setOnClickListener(@NonNull OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public int getDataSize() {
        return mPhotos.size();
    }

    @Override
    public int getDataViewResId(int viewType) {
        return R.layout.item_photo;
    }

    @Override
    public long getDataId(int dataPosition) {
        return mPhotos.get(dataPosition).hashCode();
    }

    @Override
    public int getDataViewType(int dataPosition) {
        return 0;
    }

    @NonNull
    @Override
    public PhotoViewHolder getDataViewHolder(@NonNull View v, int dataViewType) {
        return new PhotoViewHolder(v, mListener);
    }

    @Override
    public void bindDataVH(@NonNull PhotoViewHolder vh, int dataPosition) {
        vh.bind(mPhotos.get(dataPosition));
    }

    @Override
    public FooterVH getFooterVH(View v) {
        return new FooterProgressViewHolder(v);
    }

    @Override
    public int getFooterViewResId() {
        return R.layout.item_footer_progress;
    }

    @Override
    public void bindFooterVH(FooterVH vh) {

    }
}
