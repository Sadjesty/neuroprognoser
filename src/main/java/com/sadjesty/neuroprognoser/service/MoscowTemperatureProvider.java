package com.sadjesty.neuroprognoser.service;

import com.sadjesty.neuroprognoser.dto.WeatherDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Profile("dev")
public class MoscowTemperatureProvider implements ParameterProvider {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    @Autowired
    public MoscowTemperatureProvider(RestTemplate restTemplate, @Value("app.forecast.currency.url") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    @Override
    public double provideParameter() {
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
