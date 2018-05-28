package com.desihost.android.data.repository.weather.mock;

import com.desihost.android.data.entity.currentWeather.CurrentWeatherEntity;
import com.desihost.android.data.entity.currentWeather.TemperatureData;
import com.desihost.android.data.entity.currentWeather.WeatherCondition;
import com.desihost.android.data.entity.currentWeather.WeatherDataMapper;
import com.desihost.android.data.repository.weather.BaseWeatherRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import model.Weather;
import repository.WeatherRepository;


public class WeatherRepositoryFake extends BaseWeatherRepository implements WeatherRepository {

    public WeatherRepositoryFake(WeatherDataMapper weatherDataMapper) {
        super(weatherDataMapper);
    }

    @Override
    public Single<Weather> getCurrentWeather(String city) {
        return null;
    }

    @Override
    public Weather getCurrentWeatherFromDataSource(String city) {

        Weather weather = null;

        try {
            CurrentWeatherEntity weatherEntity = new CurrentWeatherEntity();
            TemperatureData tempData = new TemperatureData();
            WeatherCondition weatherCondition = new WeatherCondition();

            tempData.setMaxTemperature(282.33);
            tempData.setMinTemperature(279.22);
            tempData.setTemperature(280.90);

            weatherCondition.setConditionDescription("Cloudy Skies");
            weatherCondition.setConditionName("Drizzling");
            weatherCondition.setConditionDescription("Drizzling, and about to rain");
            weatherCondition.setConditionIcon("20e");
            weatherCondition.setConditionId(200);

            weatherEntity.setTemperatureData(tempData);

            List<WeatherCondition> weatherConditions = new ArrayList<>();
            weatherConditions.add(weatherCondition);

            weatherEntity.setWeatherConditions(weatherConditions);

            weather = weatherDataMapper.transform(weatherEntity);

            return weather;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return weather;
    }

}
