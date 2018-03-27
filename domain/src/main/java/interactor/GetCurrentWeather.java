package interactor;

import executor.PostExecutionThread;
import executor.ThreadExecutor;
import io.reactivex.Observable;
import model.Weather;
import repository.WeatherRepository;

public class GetCurrentWeather extends UseCase<Weather, GetCurrentWeather.Params> {

    private final WeatherRepository weatherRepository;

    GetCurrentWeather(WeatherRepository weatherRepository, ThreadExecutor threadExecutor,
                      PostExecutionThread postExecutionThread) {

        super(threadExecutor, postExecutionThread);
        this.weatherRepository = weatherRepository;

    }


    @Override
    Observable<Weather> buildUseCaseObservable(Params params) {

        // check to make sure the params are not null before you proceed to call the
        // weather repository
        return this.weatherRepository.getCurrentWeather(params.city, params.countryCode);

    }

    public static final class Params {
        private final String city;
        private String countryCode;

        private Params(String city) {
            this.city = city;
        }

        public static Params forCity(String city) {
            return new Params(city);
        }
    }
}
