package repository;

import io.reactivex.Observable;
import io.reactivex.Single;
import model.Weather;

public interface WeatherRepository {

    Single<Weather> getCurrentWeather(final String city);

}
