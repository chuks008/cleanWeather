package interactor;

import javax.inject.Inject;

import executor.PostExecutionThread;
import executor.ThreadExecutor;
import io.reactivex.Observable;
import io.reactivex.Single;
import model.Weather;
import repository.WeatherRepository;

public class GetCurrentWeather extends UseCase<Weather, String> {

    private final WeatherRepository weatherRepository;

    @Inject
    GetCurrentWeather(WeatherRepository weatherRepository, ThreadExecutor threadExecutor,
                      PostExecutionThread postExecutionThread) {

        super(threadExecutor, postExecutionThread);
        this.weatherRepository = weatherRepository;

    }


    @Override
    Single<Weather> buildUseCaseObservable(String city) {

        // check to make sure the params are not null before you proceed to call the
        // weather repository
        return this.weatherRepository.getCurrentWeather(city);

    }

}
