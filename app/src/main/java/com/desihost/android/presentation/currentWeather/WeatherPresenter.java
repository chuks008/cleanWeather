package com.desihost.android.presentation.currentWeather;

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

    @Inject
    WeatherPresenter(GetCurrentWeather getCurrentWeatherUseCase) {
        this.getCurrentWeatherUseCase = getCurrentWeatherUseCase;
    }

    public void setView(CurrentWeatherViewContract.View view) {
        this.view = view;
    }

    public CurrentWeatherViewContract.View getView() {
        return view;
    }

    @Override
    public void getCurrentWeather() {
        getCurrentWeatherUseCase.execute(new WeatherObserver(), Params.forCity("Lagos"));
    }

    @Override
    public void cancelCurrentSearch() {

    }

    private void stopLoading() {
        getView().stopLoading();
    }

    private void showCurrentWeatherInView(Weather currentWeather) {

    }

    private final class WeatherObserver extends DisposableSingleObserver<Weather> {

        @Override
        public void onSuccess(@NonNull Weather weather) {
            WeatherPresenter.this.stopLoading();
            WeatherPresenter.this.showCurrentWeatherInView(weather);
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

    }
}
