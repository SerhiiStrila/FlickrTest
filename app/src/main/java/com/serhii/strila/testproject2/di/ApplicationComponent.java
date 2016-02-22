package com.serhii.strila.testproject2.di;

import com.serhii.strila.testproject2.ui.fragment.BaseFragment;
import com.serhii.strila.testproject2.ui.fragment.PhotoListFragment;

import dagger.Component;

/**
 * Created by Serhii Strila on 03.07.15
 */
@PerApp
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class
})
public interface ApplicationComponent {

    void inject(BaseFragment fragment);

    void inject(PhotoListFragment fragment);
}
