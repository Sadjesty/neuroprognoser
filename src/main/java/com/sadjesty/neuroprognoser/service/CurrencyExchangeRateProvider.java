package com.sadjesty.neuroprognoser.service;

import com.sadjesty.neuroprognoser.dto.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Profile("dev")
public class CurrencyExchangeRateProvider implements ParameterProvider {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public CurrencyExchangeRateProvider(RestTemplate restTemplate, @Value("app.forecast.currency.url") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    @Override
    public double provideParameter() {
        // Выполнение HTTP-запроса для получения курса рубля за день
        ExchangeRateResponse response = restTemplate.getForObject(apiUrl, ExchangeRateResponse.class);

        if (response != null) {
            return response.getExchangeRate();
        }

        throw new RuntimeException("Failed to fetch currency exchange rate.");
    }
}
