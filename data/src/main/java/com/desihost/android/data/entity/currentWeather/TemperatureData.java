package com.desihost.android.data.entity.currentWeather;

import com.google.gson.annotations.SerializedName;

public class TemperatureData {

    @SerializedName("temp")
    private double temperature;

    @SerializedName("temp_min")
    private double minTemperature;

    @SerializedName("temp_max")
    private double maxTemperature;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }
}
