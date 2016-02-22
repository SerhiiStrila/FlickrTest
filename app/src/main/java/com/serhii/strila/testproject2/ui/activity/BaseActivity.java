package com.serhii.strila.testproject2.ui.activity;

import com.serhii.strila.testproject2.App;
import com.serhii.strila.testproject2.di.ApplicationComponent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by Serhii Strila on 12/16/15
 */
abstract public class BaseActivity extends RxAppCompatActivity {

    public ApplicationComponent getComponent() {
        return ((App) getApplication()).getComponent();
    }
}
