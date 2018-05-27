package com.desihost.android.presentation.currentWeather.mapper;

import com.desihost.android.presentation.currentWeather.model.CurrentWeatherView;

import org.junit.Before;
import org.junit.Test;

import model.Weather;

import static org.junit.Assert.*;

public class WeatherViewMapperTest {

    private WeatherViewMapper weatherViewMapper;
    private String weatherDescription;
    private String iconId;
    private String weatherCondition;
    private double tempMin;
    private double tempMax;
    private double temp;
    private Weather weather;

    @Before
    public void setUp() throws Exception {
        weatherViewMapper = new WeatherViewMapper();
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
    public void checkWeatherViewTransformIsNotNull() throws Exception {
        assertFalse("The resulting weather is not null", weatherViewMapper.transform(weather, CurrentWeatherView.TempUnit.CELCIUS) == null);
    }

    @Test
    public void checkIfTransformCorrectOutput() throws Exception {

        CurrentWeatherView weatherView = weatherViewMapper.transform(weather, CurrentWeatherView.TempUnit.CELCIUS);

        assertEquals(weatherView.getDescription(), weatherDescription);
        assertEquals(weatherView.getCondition(), weatherCondition);
        assertEquals(weatherView.getIconId(), iconId);

    }

    @Test
    public void checkTempTransformToCelcius() throws Exception {

        CurrentWeatherView weatherView = weatherViewMapper.transform(weather, CurrentWeatherView.TempUnit.CELCIUS);

        assertEquals(weatherView.getTempMax(), 9);
        assertEquals(weatherView.getTempMin(), 5);
        assertEquals(weatherView.getTemp(), 8);

    }

    @Test
    public void checkTempTransformToFahrenheit() throws Exception {

        CurrentWeatherView weatherView = weatherViewMapper.transform(weather, CurrentWeatherView.TempUnit.FAHRENHEIT);

        assertEquals(weatherView.getTempMax(), 66);
        assertEquals(weatherView.getTempMin(), 57);
        assertEquals(weatherView.getTemp(), 63);

    }
}