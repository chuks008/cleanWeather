package repository;

import io.reactivex.Observable;
import model.Weather;

public interface WeatherRepository {

    Observable<Weather> getCurrentWeather(final String city,
                                          final String countryCode);

}
