package com.desihost.android.presentation.currentWeather.model;

public class CurrentWeatherView {

    public enum TempUnit {
        CELCIUS,
        FAHRENHEIT
    }

    private String temp;
    private String tempMin;
    private String tempMax;
    private String description;
    private String iconId;
    private String condition;
    private int conditionColor;
    private int backgroundColor;

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
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
