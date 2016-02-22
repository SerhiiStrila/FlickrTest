package com.serhii.strila.testproject2.ui.adapter.viewholder;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.serhii.strila.testproject2.R;
import com.serhii.strila.testproject2.model.pojo.Photo;
import com.serhii.strila.testproject2.model.pojo.PhotoUtil;
import com.serhii.strila.testproject2.ui.listener.OnItemClickListener;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Serhii Strila on 1/26/16
 */
public class PhotoViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.img_photo)
    ImageView photo;

    public PhotoViewHolder(View itemView, @Nullable OnItemClickListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        setListener(listener);
    }

    public void bind(Photo photo) {
        Picasso.with(itemView.getContext())
                .load(PhotoUtil.getImageUrl(photo))
                .placeholder(R.drawable.ic_person)
                .centerCrop()
                .fit()
                .into(this.photo);
    }

    private void setListener(@Nullable OnItemClickListener listener) {
        if (listener != null) {
            itemView.setOnClickListener(v -> listener.onItemClick(getAdapterPosition()));
        }
    }
}
