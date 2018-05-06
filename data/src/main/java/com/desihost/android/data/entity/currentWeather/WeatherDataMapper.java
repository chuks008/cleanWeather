package com.desihost.android.data.entity.currentWeather;

import javax.inject.Inject;

import model.Weather;

public class WeatherDataMapper {

    @Inject
    WeatherDataMapper() {
    }

    public Weather transform(CurrentWeatherEntity currentWeatherEntity) {
        Weather weather = null;

        if(currentWeatherEntity != null) {
            weather = new Weather();

            weather.setDescription(currentWeatherEntity.getWeatherConditions().get(0).getConditionDescription());
            weather.setCondition(currentWeatherEntity.getWeatherConditions().get(0).getConditionName());
            weather.setTemp(currentWeatherEntity.getTemperatureData().getTemperature());
            weather.setTempMin(currentWeatherEntity.getTemperatureData().getMinTemperature());
            weather.setTempMax(currentWeatherEntity.getTemperatureData().getMaxTemperature());
            weather.setIconId(currentWeatherEntity.getWeatherConditions().get(0).getConditionIcon());
        }

        return weather;
    }
}
