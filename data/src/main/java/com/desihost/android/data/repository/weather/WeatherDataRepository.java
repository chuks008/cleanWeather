package com.desihost.android.data.repository.weather;

import com.desihost.android.data.entity.currentWeather.CurrentWeatherEntity;
import com.desihost.android.data.entity.currentWeather.WeatherDataMapper;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observable;
import model.Weather;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import repository.WeatherRepository;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// actual implementation of the weather repository in the app
// it could have been called WeatherRepositoryImpl, but this kind of make sense too
// Since the domain layer knows nothing about the data layer, the WeatherRepository interface
// serves as a way to connect the two layers through a use case

public class WeatherDataRepository implements WeatherRepository {

    // create a mapper to transform the data from the repository (data layer) into something comprehensible
    // to the domain layer (domain layer)

    // in this case the term "entity" refers to the java POJO that will be mapped to the response
    // of the weather api

    private final WeatherDataMapper weatherDataMapper;
    private Retrofit retrofit;
    private WeatherApi weatherApi;


    @Inject
    WeatherDataRepository(WeatherDataMapper weatherDataMapper) {
        this.weatherDataMapper = weatherDataMapper;

        retrofit = new Retrofit.Builder()
                    .baseUrl("api.openweathermap.org")
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();

        weatherApi = retrofit.create(WeatherApi.class);

    }

    @Override
    public Observable<Weather> getCurrentWeather(String city, String countryCode) {
        return getCurrentFromApi(city, countryCode);
    }

    private Observable<Weather> getCurrentFromApi(String city, String countryCode) {
        return weatherApi.getCurrentWeather(city, countryCode).map(weatherDataMapper::transform);
    }

}
