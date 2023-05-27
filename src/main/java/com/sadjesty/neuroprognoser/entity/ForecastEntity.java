package com.sadjesty.neuroprognoser.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;


@Entity
@Data
@Table(name = "forecast")
public class ForecastEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date forecastDate;
    private double temperature;
    private double exchangeRate;
    private double holidayCoefficient;
    private double paydayCoefficient;
    private int actualOrders;
    private int predictedOrders;
}
