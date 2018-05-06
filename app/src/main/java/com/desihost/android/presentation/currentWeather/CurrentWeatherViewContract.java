package com.desihost.android.presentation.currentWeather;

public interface CurrentWeatherViewContract {

    interface View {

        void startLoading();
        void showError();
        void stopLoading();

        void renderWeather(String weatherString);
    }

    interface UserClickListener {

        void getCurrentWeather(String city);
        void cancelCurrentSearch();
    }
}
