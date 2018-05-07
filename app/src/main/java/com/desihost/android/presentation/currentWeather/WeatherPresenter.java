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

    @Inject
    WeatherPresenter(GetCurrentWeather getCurrentWeatherUseCase,
                     WeatherViewMapper weatherViewMapper) {
        this.getCurrentWeatherUseCase = getCurrentWeatherUseCase;
        this.weatherViewMapper = weatherViewMapper;
    }

    public void setView(CurrentWeatherViewContract.View view) {
        this.view = view;
    }

    public CurrentWeatherViewContract.View getView() {
        return view;
    }

    @Override
    public void getCurrentWeather(String city) {
        fetchWeather(city);
    }

    private void fetchWeather(String city) {
        getCurrentWeatherUseCase.execute(new WeatherObserver(), Params.forCity(city));
    }

    @Override
    public void cancelCurrentSearch() {

    }

    private void stopLoading() {
        getView().stopLoading();
    }

    public void showCurrentWeatherInView(Weather currentWeather) {
        view.renderWeather(weatherViewMapper.transform(currentWeather, temperatureUnit));
    }

    private final class WeatherObserver extends DisposableSingleObserver<Weather> {

        @Override
        public void onSuccess(@NonNull Weather weather) {
            WeatherPresenter.this.stopLoading();
            WeatherPresenter.this.showCurrentWeatherInView(weather);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            view.showError();
        }

    }
}
