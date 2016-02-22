package com.serhii.strila.testproject2;

import android.app.Application;

import com.serhii.strila.testproject2.di.ApplicationComponent;
import com.serhii.strila.testproject2.di.ApplicationModule;
import com.serhii.strila.testproject2.di.DaggerApplicationComponent;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Serhii Strila on 1/26/16
 */
public class App extends Application {

    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }

    private void initRealm() {
        RealmConfiguration configuration = new RealmConfiguration.Builder(this)
                .name("TestData")
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
