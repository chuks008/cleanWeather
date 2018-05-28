package com.desihost.android.apipractical;

import android.support.v7.app.AppCompatActivity;

import com.desihost.android.MyApp;
import com.desihost.android.di.components.ApplicationComponent;


public class BaseActivity extends AppCompatActivity {

    public ApplicationComponent getAppComponent() {
        return ((MyApp) getApplicationContext()).getAppComponent();
    }
}
