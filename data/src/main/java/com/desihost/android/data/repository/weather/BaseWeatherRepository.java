package com.desihost.android.data.repository.weather;

import com.desihost.android.data.entity.currentWeather.CurrentWeatherEntity;
import com.desihost.android.data.entity.currentWeather.WeatherDataMapper;

import java.io.IOException;

import io.reactivex.Single;
import model.Weather;
import repository.WeatherRepository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public abstract class BaseWeatherRepository implements WeatherRepository {

    protected final WeatherDataMapper weatherDataMapper;


    public BaseWeatherRepository(WeatherDataMapper weatherDataMapper) {
        this.weatherDataMapper = weatherDataMapper;

    }

    @Override
    public Single<Weather> getCurrentWeather(String city) {
        return null;
    }

    protected abstract Weather getCurrentWeatherFromDataSource(String city);
}
