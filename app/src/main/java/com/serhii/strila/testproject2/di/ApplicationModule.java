package com.serhii.strila.testproject2.di;

import com.serhii.strila.testproject2.App;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Serhii Strila on 1/26/16
 */
@Module
public class ApplicationModule {

    private App mApp;

    public ApplicationModule(App app) {
        mApp = app;
    }

    @Provides
    @PerApp
    App provideApplication() {
        return mApp;
    }
}
