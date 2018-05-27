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
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class WeatherPresenterTest {

    @Mock
    private GetCurrentWeather getCurrentWeatherUseCase;

    @Mock
    private CurrentWeatherViewContract.View view;

    @Mock
    private WeatherViewMapper weatherViewMapper;

    private WeatherPresenter weatherPresenter;
    private String currentCity;

    private String weatherDescription;
    private String iconId;
    private String weatherCondition;
    private double tempMin;
    private double tempMax;
    private double temp;
    private Weather weather;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        weatherPresenter = new WeatherPresenter(getCurrentWeatherUseCase, weatherViewMapper);
        weatherPresenter.setView(view);
        currentCity = "Munich";
        weatherPresenter.setCurrentCity(currentCity);

        weatherDescription = "All clear skies today";
        iconId = "21e";
        weatherCondition = "Sunny, with small chance of precipitation";

        tempMin = 287.40;
        tempMax = 292.32;
        temp = 290.68;

        weather = new Weather();

        weather.setCondition(weatherCondition);
        weather.setIconId(iconId);
        weather.setDescription(weatherDescription);
        weather.setTemp(temp);
        weather.setTempMin(tempMin);
        weather.setTempMax(tempMax);
    }

    @Test
    public void fetchValidDataShouldLoadIntoView() throws Exception {

        // create fake data for passing to the presenter
        weatherPresenter.getCurrentWeather();
        verify(view).startLoading();
        verify(getCurrentWeatherUseCase).execute(any(DisposableSingleObserver.class), eq(currentCity));

    }

    @Test
    public void fetchedWeatherRendersToView() throws Exception {

        CurrentWeatherView currentWeatherView = new CurrentWeatherView();

        when(weatherViewMapper.transform(weather, CurrentWeatherView.TempUnit.CELCIUS)).thenReturn(currentWeatherView);
        weatherPresenter.showCurrentWeatherInView(weather);

        verify(view).stopLoading();
        verify(weatherViewMapper).transform(weather, CurrentWeatherView.TempUnit.CELCIUS);
        verify(view).renderWeather(currentCity, currentWeatherView);
        verifyNoMoreInteractions(view);

    }

    @Test
    public void fetchWeatherShouldReturnError() throws Exception {
        // the application failed to get data from the server, so it calls the showRenderError
        // so we verify if the show error in the view has been called
        weatherPresenter.showRenderError();
        verify(view).showError();
    }

}