package com.desihost.android.data.entity.currentWeather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrentWeatherEntity {

    @SerializedName("weather")
    private List<WeatherCondition> weatherConditions;

    @SerializedName("main")
    private TemperatureData temperatureData;

    public List<WeatherCondition> getWeatherConditions() {
        return weatherConditions;
    }

    public void setWeatherConditions(List<WeatherCondition> weatherConditions) {
        this.weatherConditions = weatherConditions;
    }

    public TemperatureData getTemperatureData() {
        return temperatureData;
    }

    public void setTemperatureData(TemperatureData temperatureData) {
        this.temperatureData = temperatureData;
    }
}
