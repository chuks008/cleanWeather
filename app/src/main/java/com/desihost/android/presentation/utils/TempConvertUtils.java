package com.desihost.android.presentation.utils;

public class TempConvertUtils {

    public static int convertToFahrenheit(double kelvin) {
        return (int) Math.round((kelvin - 273) * 9/5) + 32;
    }

    public static int convertToCelcius(double kelvin) {
        return Math.round((int) kelvin - 273);
    }
}
