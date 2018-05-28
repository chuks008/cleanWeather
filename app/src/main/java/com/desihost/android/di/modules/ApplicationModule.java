package com.desihost.android.di.modules;

import android.content.Context;

import com.desihost.android.MyApp;
import com.desihost.android.data.executor.BackgroundExecutor;
import com.desihost.android.data.repository.weather.prod.WeatherDataRepository;
import com.desihost.android.executor.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import executor.PostExecutionThread;
import executor.ThreadExecutor;
import repository.WeatherRepository;

// this module takes one parameter to initialize the dependency injection, which is the application
// now provide the methods to create instances of the classes in the object graph described in the
// object graph in the application component

// everywhere in the app you see the @Inject annotation, the constructor in there has provided a way
// to instantiate the class so the dependency injection framework will take care of it once the
// @Inject annotation is visible

@Module
public class ApplicationModule {

    private final MyApp application;

    public ApplicationModule(MyApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExectutor(BackgroundExecutor backgroundExecutor) {
        return backgroundExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExuctionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository(WeatherDataRepository weatherDataRepository) {
        return weatherDataRepository;
    }

}
