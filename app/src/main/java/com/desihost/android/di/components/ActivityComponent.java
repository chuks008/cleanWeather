package com.desihost.android.di.components;

import android.app.Activity;

import com.desihost.android.di.modules.ActivityModule;
import com.desihost.android.di.scopes.PerActivity;

import dagger.Component;

// this component will live as long as an activity containing its dependencies lives in is still alive
//

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}
