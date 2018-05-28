package interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import executor.PostExecutionThread;
import executor.ThreadExecutor;
import io.reactivex.schedulers.Schedulers;
import repository.WeatherRepository;

import static org.junit.Assert.*;

public class GetCurrentWeatherTest {

    private GetCurrentWeather getCurrentWeatherUseCase;

    @Mock
    private WeatherRepository weatherRepository;

    @Mock
    private PostExecutionThread mockExecutionThread;

    @Mock
    private ThreadExecutor mockBackExecution;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getCurrentWeatherUseCase = new GetCurrentWeather(weatherRepository, mockBackExecution, mockExecutionThread);

    }

    @Test
    public void getWeatherFromReposIsCalledOnce() throws Exception {
        getCurrentWeatherUseCase.buildUseCaseObservable("Munich");
        verify(weatherRepository).getCurrentWeather("Munich");
        verifyNoMoreInteractions(weatherRepository);
    }
}