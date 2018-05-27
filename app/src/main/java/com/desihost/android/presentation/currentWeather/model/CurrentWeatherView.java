package com.desihost.android.presentation.currentWeather.model;

public class CurrentWeatherView {

    public enum TempUnit {
        CELCIUS,
        FAHRENHEIT
    }

    private int temp;
    private int tempMin;
    private int tempMax;
    private String description;
    private String iconId;
    private String condition;
    private int conditionColor;
    private int backgroundColor;

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
