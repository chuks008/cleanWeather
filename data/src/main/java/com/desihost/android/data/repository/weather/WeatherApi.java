package com.desihost.android.data.repository.weather;

import com.desihost.android.data.entity.currentWeather.CurrentWeatherEntity;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    String API_KEY = "your-api-key";

    @GET("data/2.5/weather?appid"+API_KEY)
    Call<CurrentWeatherEntity> getCurrentWeather(@Query("q") String city);

}
