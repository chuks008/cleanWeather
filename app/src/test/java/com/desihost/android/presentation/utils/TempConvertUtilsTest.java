package com.desihost.android.presentation.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class TempConvertUtilsTest {

    private static final double KELVIN_TEMP = 281.15;

    @Test
    public void testConvertToFahrenheit() {
        assertEquals(47, TempConvertUtils.convertToFahrenheit(KELVIN_TEMP));
    }

    @Test
    public void testConverToCelcius() {
        assertEquals(8, TempConvertUtils.convertToCelcius(KELVIN_TEMP));
    }
}