package com.desihost.android.presentation.currentWeather;

import com.desihost.android.presentation.currentWeather.mapper.WeatherViewMapper;
import com.desihost.android.presentation.currentWeather.model.CurrentWeatherView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import interactor.GetCurrentWeather;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import model.Weather;
import repository.WeatherRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

public class WeatherPresenterTest {

    @Mock
    private GetCurrentWeather getCurrentWeatherUseCase;

    @Mock
    private CurrentWeatherViewContract.View view;

    @Mock
    private WeatherViewMapper weatherViewMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void fetchValidDataShouldLoadIntoView() {

        // create fake data for passing to the presenter


        WeatherPresenter weatherPresenter = new WeatherPresenter(getCurrentWeatherUseCase, weatherViewMapper);
        weatherPresenter.setView(view);

        weatherPresenter.getCurrentWeather("Munich");
        verify(getCurrentWeatherUseCase).execute(any(DisposableSingleObserver.class), any(String.class));
        weatherPresenter.showCurrentWeatherInView(any(Weather.class));
        verify(view).renderWeather(any(CurrentWeatherView.class));
//        verify(view).renderWeather(any(String.class));

    }
}