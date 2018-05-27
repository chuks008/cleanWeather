package com.desihost.android.presentation.currentWeather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.desihost.android.apipractical.R;
import com.desihost.android.presentation.currentWeather.model.CurrentWeatherView;

import javax.inject.Inject;

public class CurrentWeatherActivity extends AppCompatActivity implements CurrentWeatherViewContract.View {

    @Inject
    WeatherPresenter weatherPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherPresenter.setView(this);
        weatherPresenter.getCurrentWeather("Lagos");
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void renderWeather(CurrentWeatherView currentWeatherView) {

    }
}
