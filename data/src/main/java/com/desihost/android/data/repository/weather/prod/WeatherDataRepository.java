package com.desihost.android.data.repository.weather.prod;

import com.desihost.android.data.entity.currentWeather.CurrentWeatherEntity;
import com.desihost.android.data.entity.currentWeather.WeatherDataMapper;
import com.desihost.android.data.repository.weather.BaseWeatherRepository;
import com.desihost.android.data.repository.weather.WeatherApi;
import com.google.gson.Gson;

import org.json.JSONObject;

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

public class WeatherDataRepository extends BaseWeatherRepository implements WeatherRepository {

    // create a mapper to transform the data from the repository (data layer) into something comprehensible
    // to the domain layer (domain layer)

    // in this case the term "entity" refers to the java POJO that will be mapped to the response
    // of the weather api

    private Retrofit retrofit;
    private WeatherApi weatherApi;

    @Inject
    WeatherDataRepository(WeatherDataMapper weatherDataMapper) {
       super(weatherDataMapper);

        retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.openweathermap.org")
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();

        weatherApi = retrofit.create(WeatherApi.class);

    }

    @Override
    public Single<Weather> getCurrentWeather(final String city) {
        return Single.fromCallable(new Callable<Weather>() {
            @Override
            public Weather call() throws Exception {
                return getCurrentWeatherFromDataSource(city);
            }
        });
    }

    @Override
    public Weather getCurrentWeatherFromDataSource(String city) {

        Weather fetchedWeather = null;

        try {
            Call<CurrentWeatherEntity> call = weatherApi.getCurrentWeather(city);
            Response<CurrentWeatherEntity> weatherResponse = call.execute();

            if(weatherResponse.isSuccessful()) {
                System.out.println("Weather Response Success");
                fetchedWeather = weatherDataMapper.transform(weatherResponse.body());
                return fetchedWeather;
            } else {
                try {
                    JSONObject errorObject = new JSONObject(weatherResponse.errorBody().string());
                    System.out.println("Error: "+ errorObject.getString("message"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Weather Response fail");

        return fetchedWeather;
    }

}
