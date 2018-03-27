package com.desihost.android.presentation.currentWeather;

public interface CurrentWeatherViewContract {

    interface View {

        void startLoading();
        void showError();
        void stopLoading();
    }

    interface UserClickListener {

        void getCurrentWeather();
        void cancelCurrentSearch();
    }
}
