package com.desihost.android.presentation.currentWeather.mapper;

import com.desihost.android.presentation.currentWeather.model.CurrentWeatherView;
import com.desihost.android.presentation.utils.TempConvertUtils;

import javax.inject.Inject;

import model.Weather;

public class WeatherViewMapper {

    @Inject
    WeatherViewMapper(){}

    public CurrentWeatherView transform(Weather weather, CurrentWeatherView.TempUnit tempUnit) {
        CurrentWeatherView currentWeatherView = null;

        if(weather != null && tempUnit != null) {
            currentWeatherView = new CurrentWeatherView();
            currentWeatherView.setIconId(weather.getIconId());
            currentWeatherView.setDescription(weather.getDescription());
            currentWeatherView.setCondition(weather.getCondition());

            // current temperature default is in kelvin

            int tempMaxConvert;
            int tempMinConvert;
            int currentTempConvert;

            if(tempUnit == CurrentWeatherView.TempUnit.FAHRENHEIT) {
                tempMaxConvert = TempConvertUtils.convertToFahrenheit(weather.getTempMax());
                tempMinConvert = TempConvertUtils.convertToFahrenheit(weather.getTempMin());
                currentTempConvert = TempConvertUtils.convertToFahrenheit(weather.getTemp());
            } else {
                tempMaxConvert =  convertToCelcius(weather.getTempMax());
                tempMinConvert = convertToCelcius(weather.getTempMin());
                currentTempConvert = convertToCelcius(weather.getTemp());
            }

            currentWeatherView.setTempMax(String.valueOf(tempMaxConvert));
            currentWeatherView.setTempMin(String.valueOf(tempMinConvert));
            currentWeatherView.setTemp(String.valueOf(currentTempConvert));
        }

        return currentWeatherView;
    }

    public int convertToFahrenheit(double kelvin) {
        return (int) (1.8 * (kelvin - 273) + 32);
    }

    public int convertToCelcius(double kelvin) {
        return (int) kelvin - 273;
    }
}
