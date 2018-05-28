package com.desihost.android;

import android.app.Application;

import com.desihost.android.di.components.ApplicationComponent;
import com.desihost.android.di.components.DaggerApplicationComponent;
import com.desihost.android.di.modules.ApplicationModule;


public class MyApp extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getAppComponent() {
        return this.applicationComponent;
    }
}
