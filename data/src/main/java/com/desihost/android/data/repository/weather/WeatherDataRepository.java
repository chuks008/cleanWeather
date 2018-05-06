package com.desihost.android.data.repository.weather;

import com.desihost.android.data.entity.currentWeather.CurrentWeatherEntity;
import com.desihost.android.data.entity.currentWeather.WeatherDataMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import model.Weather;
import repository.WeatherRepository;
import retrofit2.Call;
import retrofit2.Response;
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
    public Single<Weather> getCurrentWeather(String city, String countryCode) {
        return Single.fromCallable(new Callable<Weather>() {
            @Override
            public Weather call() throws Exception {
                return getCurrentFromApi(city, countryCode);
            }
        });
    }

    private Weather getCurrentFromApi(String city, String countryCode) {

        Weather fetchedWeather = new Weather();

        try {
            Call<CurrentWeatherEntity> call = weatherApi.getCurrentWeather(city, countryCode);
            Response<CurrentWeatherEntity> weatherResponse = call.execute();

            if(weatherResponse.isSuccessful()) {
                fetchedWeather = weatherDataMapper.transform(weatherResponse.body());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fetchedWeather;
    }

}
