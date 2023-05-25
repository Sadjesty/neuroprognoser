package com.sadjesty.neuroprognoser.service;

import com.sadjesty.neuroprognoser.dto.WeatherDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MoscowTemperatureProvider implements ParameterProvider {

    private final RestTemplate restTemplate;

    @Autowired
    public MoscowTemperatureProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public double provideParameter() {
        String apiUrl = "https://api.weather.com/moscow/temperature"; // Пример URL для получения данных о температуре

        WeatherDataResponse weatherDataResponse = restTemplate.getForObject(apiUrl, WeatherDataResponse.class);

        if (weatherDataResponse != null) {
            double[] temperatures = weatherDataResponse.getTemperatures();
            if (temperatures.length > 0) {
                double sum = 0.0;
                for (double temperature : temperatures) {
                    sum += temperature;
                }
                return sum / temperatures.length;
            }
        }

        return 0.0;
    }
}
