package com.desihost.android.presentation.currentWeather;

import com.desihost.android.presentation.currentWeather.model.CurrentWeatherView;

public interface CurrentWeatherViewContract {

    interface View {

        void startLoading();
        void showError();
        void stopLoading();

        void renderWeather(String city, CurrentWeatherView currentWeatherView);
    }

    interface UserClickListener {

        void getCurrentWeather();
        void setCurrentCity(String city);
        void cancelCurrentSearch();
    }
}
