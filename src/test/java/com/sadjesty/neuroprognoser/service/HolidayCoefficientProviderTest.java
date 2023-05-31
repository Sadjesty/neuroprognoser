package com.sadjesty.neuroprognoser.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HolidayCoefficientProviderTest {
    HolidayCoefficientProvider holidayCoefficientProvider = new HolidayCoefficientProvider();

    @Test
    void testProvideParameter() {
        double result = holidayCoefficientProvider.provideParameter();
        Assertions.assertEquals(0d, result);
    }
}