package com.desihost.android.di.components;


import android.content.Context;

import com.desihost.android.apipractical.BaseActivity;
import com.desihost.android.di.modules.ApplicationModule;
import com.desihost.android.presentation.currentWeather.CurrentWeatherActivity;

import javax.inject.Singleton;

import dagger.Component;
import executor.PostExecutionThread;
import executor.ThreadExecutor;
import repository.WeatherRepository;

// create the application component which will be injected app-wide
// it is also a singleton so that there is only one instance created
// an inject method is created to make it accessible to the base activity used in the app
// all fragments will depend on a particular type of activity which will have access to
// several components the application module makes available
// here it makes the application context available, as well as:
// the ThreadExecutor to make all api calls or db calls on the background thread
// the PostExecutorThread will perform work in the ui thread, in this case the android main thread
// The weather repository will need to be available for which ever presenter will need it
// in short, all the data access points can be exposed to the object graph app-wide with this component

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(CurrentWeatherActivity baseActivity);

    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExectionThread();
    WeatherRepository weatherRepository();

}
