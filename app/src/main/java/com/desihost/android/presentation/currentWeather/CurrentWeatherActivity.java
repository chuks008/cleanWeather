package com.desihost.android.presentation.currentWeather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.desihost.android.apipractical.R;
import com.desihost.android.presentation.currentWeather.model.CurrentWeatherView;

import javax.inject.Inject;

public class CurrentWeatherActivity extends AppCompatActivity implements CurrentWeatherViewContract.View, View.OnClickListener {

    private TextView currentTemp, minTemp, maxTemp, condition;
    private TextView cityName;
    private ImageButton getCurrentWeatherButton;
    private Toolbar currentWeatherToolbar;
    private LinearLayout weatherContainer, loadingLayout;
    private ProgressBar loadingBar;
    private TextView loadingStatus;

    @Inject
    WeatherPresenter weatherPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentTemp = (TextView) findViewById(R.id.current_temperature_textview);
        minTemp = (TextView) findViewById(R.id.min_temp_textview);
        maxTemp = (TextView) findViewById(R.id.max_temp_textview);
        condition = (TextView) findViewById(R.id.condition_textview);
        loadingLayout = (LinearLayout) findViewById(R.id.loading_container);
        loadingBar = (ProgressBar) loadingLayout.findViewById(R.id.weather_loading_progress);
        loadingStatus = (TextView) loadingLayout.findViewById(R.id.loading_status_message);

        weatherContainer = (LinearLayout) findViewById(R.id.weather_detail_container);

        currentWeatherToolbar = (Toolbar) findViewById(R.id.current_weather_toolbar);
        cityName = (TextView) currentWeatherToolbar.findViewById(R.id.city_name_textview);
        getCurrentWeatherButton = (ImageButton) currentWeatherToolbar.findViewById(R.id.get_weather_button);
        getCurrentWeatherButton.setOnClickListener(this);

        weatherPresenter.setView(this);
        weatherPresenter.setCurrentCity("Lagos");
        weatherPresenter.getCurrentWeather();

    }

    @Override
    public void startLoading() {
        loadingLayout.setVisibility(View.VISIBLE);

        loadingBar.setVisibility(View.VISIBLE);
        loadingStatus.setText("Loading");

        weatherContainer.setVisibility(View.GONE);
    }

    @Override
    public void showError() {

        loadingBar.setVisibility(View.GONE);
        loadingStatus.setText("Error loading weather. Try again");
    }

    @Override
    public void stopLoading() {

        loadingLayout.setVisibility(View.GONE);
        weatherContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void renderWeather(String currentCity, CurrentWeatherView currentWeatherView) {

        cityName.setText(currentCity);
        currentTemp.setText(currentWeatherView.getTemp());
        maxTemp.setText(currentWeatherView.getTempMax());
        minTemp.setText(currentWeatherView.getTempMin());
        condition.setText(currentWeatherView.getCondition());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_weather_button:
                weatherPresenter.setCurrentCity("London");
                weatherPresenter.getCurrentWeather();
                break;
        }
    }
}
