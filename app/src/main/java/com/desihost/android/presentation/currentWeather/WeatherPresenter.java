package com.desihost.android.presentation.currentWeather;

import interactor.DefaultObserver;
import interactor.GetCurrentWeather;
import io.reactivex.annotations.NonNull;
import model.Weather;

import static interactor.GetCurrentWeather.*;

public class WeatherPresenter implements CurrentWeatherViewContract.UserClickListener {

    private CurrentWeatherViewContract.View view;
    private GetCurrentWeather getCurrentWeatherUseCase;

    public WeatherPresenter(CurrentWeatherViewContract.View view) {
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

    private final class WeatherObserver extends DefaultObserver<Weather> {
        @Override
        public void onComplete() {
            WeatherPresenter.this.stopLoading();
        }

        @Override
        public void onError(@NonNull Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(@NonNull Weather weather) {
            WeatherPresenter.this.showCurrentWeatherInView(weather);
        }
    }
}
