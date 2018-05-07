package com.desihost.android.presentation.currentWeather;

import com.desihost.android.presentation.currentWeather.model.CurrentWeatherView;

public interface CurrentWeatherViewContract {

    interface View {

        void startLoading();
        void showError();
        void stopLoading();

        void renderWeather(CurrentWeatherView currentWeatherView);
    }

    interface UserClickListener {

        void getCurrentWeather(String city);
        void cancelCurrentSearch();
    }
}
