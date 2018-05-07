package com.desihost.android.presentation.currentWeather.mapper;

import com.desihost.android.presentation.currentWeather.model.CurrentWeatherView;

import javax.inject.Inject;

import model.Weather;

public class WeatherViewMapper {

    @Inject
    WeatherViewMapper(){}

    public CurrentWeatherView transform(Weather weather, CurrentWeatherView.TempUnit tempUnit) {
        CurrentWeatherView currentWeatherView = null;

        if(weather != null) {
            currentWeatherView = new CurrentWeatherView();
            currentWeatherView.setIconId(weather.getIconId());
            currentWeatherView.setDescription(weather.getDescription());
            currentWeatherView.setCondition(weather.getCondition());

            // current temperature default is in kelvin

            double tempMaxConvert;
            double tempMinConvert;
            double currentTempConvert;

            if(tempUnit == CurrentWeatherView.TempUnit.FAHRENHEIT) {
                tempMaxConvert =  convertToFahrenheit(weather.getTempMax());
                tempMinConvert = convertToFahrenheit(weather.getTempMin());
                currentTempConvert = convertToFahrenheit(weather.getTemp());
            } else {
                tempMaxConvert =  convertToCelcius(weather.getTempMax());
                tempMinConvert = convertToCelcius(weather.getTempMin());
                currentTempConvert = convertToCelcius(weather.getTemp());
            }


            currentWeatherView.setTempMax(tempMaxConvert);
            currentWeatherView.setTempMin(tempMinConvert);
            currentWeatherView.setTemp(currentTempConvert);
        }

        return currentWeatherView;
    }

    private double convertToFahrenheit(double kelvin) {
        return (9/5) * (kelvin - 273) + 32;
    }

    private double convertToCelcius(double kelvin) {
        return kelvin - 273;
    }
}
