package com.desihost.android.presentation.currentWeather;

import com.desihost.android.presentation.currentWeather.mapper.WeatherViewMapper;
import com.desihost.android.presentation.currentWeather.model.CurrentWeatherView;

import javax.inject.Inject;

import interactor.DefaultObserver;
import interactor.GetCurrentWeather;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import model.Weather;

import static interactor.GetCurrentWeather.*;

public class WeatherPresenter implements CurrentWeatherViewContract.UserClickListener {

    private CurrentWeatherViewContract.View view;
    private GetCurrentWeather getCurrentWeatherUseCase;
    private WeatherViewMapper weatherViewMapper;
    private CurrentWeatherView.TempUnit temperatureUnit = CurrentWeatherView.TempUnit.CELCIUS;
    private String currentCity;

    @Inject
    WeatherPresenter(GetCurrentWeather getCurrentWeatherUseCase,
                     WeatherViewMapper weatherViewMapper) {
        this.getCurrentWeatherUseCase = getCurrentWeatherUseCase;
        this.weatherViewMapper = weatherViewMapper;
    }

    public void setView(CurrentWeatherViewContract.View view) {
        this.view = view;
    }

    @Override
    public void getCurrentWeather() {
        view.startLoading();
        getCurrentWeatherUseCase.execute(new WeatherObserver(), currentCity);

    }

    @Override
    public void cancelCurrentSearch() {

    }

    @Override
    public void setCurrentCity(String city) {
        this.currentCity = city;
    }

    public void showCurrentWeatherInView(Weather currentWeather) {
        view.stopLoading();
        view.renderWeather(currentCity, weatherViewMapper.transform(currentWeather, temperatureUnit));
    }

    public void showRenderError() {
        view.showError();
    }

    private final class WeatherObserver extends DisposableSingleObserver<Weather> {

        @Override
        public void onSuccess(@NonNull Weather weather) {
            showCurrentWeatherInView(weather);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            showRenderError();
        }

    }
}
