package com.sadjesty.neuroprognoser.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PaydayCoefficientProviderTest {
    PaydayCoefficientProvider paydayCoefficientProvider = new PaydayCoefficientProvider();

    @Test
    void testProvideParameter() {
        double result = paydayCoefficientProvider.provideParameter();
        Assertions.assertEquals(0d, result);
    }
}
