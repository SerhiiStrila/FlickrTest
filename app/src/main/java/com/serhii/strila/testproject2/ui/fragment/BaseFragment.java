package com.serhii.strila.testproject2.ui.fragment;

import android.os.Bundle;

import com.serhii.strila.testproject2.App;
import com.serhii.strila.testproject2.di.ApplicationComponent;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;

/**
 * Created by Serhii Strila on 12/16/15
 */
abstract public class BaseFragment extends RxFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    public ApplicationComponent getComponent() {
        return ((App) getActivity().getApplication()).getComponent();
    }
}
