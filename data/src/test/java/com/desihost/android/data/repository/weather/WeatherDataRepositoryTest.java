package com.desihost.android.data.repository.weather;

import com.desihost.android.data.entity.currentWeather.WeatherDataMapper;
import com.desihost.android.data.repository.weather.mock.WeatherRepositoryFake;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import model.Weather;
import static org.junit.Assert.*;

public class WeatherDataRepositoryTest {

    private WeatherRepositoryFake fakeWeatherRepo;

    @Mock
    private WeatherDataMapper weatherDataMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        fakeWeatherRepo = new WeatherRepositoryFake(weatherDataMapper);

    }

    @Test
    public void weatherRepoShouldNotReturnNull() throws Exception {
        Weather weather = fakeWeatherRepo.getCurrentWeatherFromDataSource("Munich");
        assertNotNull(weather);
    }


}