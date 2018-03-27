package com.desihost.android.data.repository.weather;

import com.desihost.android.data.entity.currentWeather.CurrentWeatherEntity;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("data/2.5/weather")
    Observable<CurrentWeatherEntity> getCurrentWeather(@Query("q") String city,
                                                       @Query("appid") String appId);

}
