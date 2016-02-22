package com.serhii.strila.testproject2.ui.adapter.viewholder;

import android.support.v7.widget.CardView;
import android.view.View;

import com.serhii.strila.testproject2.R;
import com.serhii.strila.testproject2.ui.adapter.UltimateAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Serhii Strila on 1/26/16
 */
public class FooterProgressViewHolder extends UltimateAdapter.FooterVH {

    @Bind(R.id.card_view)
    public CardView cardView;

    public FooterProgressViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void hideFooter(boolean hide) {
        cardView.setVisibility(hide ? View.GONE : View.VISIBLE);
    }
}
