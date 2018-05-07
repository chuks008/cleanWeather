package com.desihost.android.presentation.currentWeather.model;

public class CurrentWeatherView {

    public enum TempUnit {
        CELCIUS,
        FAHRENHEIT
    }

    private double temp;
    private double tempMin;
    private double tempMax;
    private String description;
    private String iconId;
    private String condition;
    private int conditionColor;
    private int backgroundColor;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
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
