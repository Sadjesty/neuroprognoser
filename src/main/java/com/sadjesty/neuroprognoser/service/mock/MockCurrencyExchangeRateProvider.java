package com.sadjesty.neuroprognoser.service.mock;

import com.sadjesty.neuroprognoser.service.CurrencyExchangeRateProvider;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("mock-services")
public class MockCurrencyExchangeRateProvider extends CurrencyExchangeRateProvider {
    public MockCurrencyExchangeRateProvider() {
        super(null, null);
    }

    @Override
    public double provideParameter() {
        return 80.00;
    }
}
