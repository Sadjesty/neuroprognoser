package com.sadjesty.neuroprognoser.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SalaryCoefficientProvider implements ParameterProvider {

    @Override
    public double provideParameter() {
        int todayDay = LocalDate.now().getDayOfMonth();
        if (todayDay >= 13 && todayDay <= 20 || todayDay >= 25 && todayDay <= 30) {
            return 1.0;
        }

        if (todayDay >= 10 && todayDay < 13 || todayDay >= 21 && todayDay <= 25) {
            return 0.5;
        }

        return 0;
    }
}
