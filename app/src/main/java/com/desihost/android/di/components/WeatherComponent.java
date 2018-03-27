package com.desihost.android.di.components;

import com.desihost.android.di.modules.ActivityModule;
import com.desihost.android.di.modules.WeatherModule;
import com.desihost.android.di.scopes.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, WeatherModule.class})
public interface WeatherComponent {
}

