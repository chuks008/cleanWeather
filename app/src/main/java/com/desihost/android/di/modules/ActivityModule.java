package com.desihost.android.di.modules;

import android.app.Activity;

import com.desihost.android.di.scopes.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity providesActivity() {
        return this.activity;
    }
}
