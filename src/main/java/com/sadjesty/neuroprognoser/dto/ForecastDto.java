package com.sadjesty.neuroprognoser.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ForecastDto {
    private Long id;
    private LocalDate forecastDate;
    private double temperature;
    private double exchangeRate;
    private double holidayCoefficient;
    private double paydayCoefficient;
    private int actualOrders;
    private int predictedOrders;
}

