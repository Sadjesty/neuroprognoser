package com.sadjesty.neuroprognoser.service.mock;

import com.sadjesty.neuroprognoser.service.MoscowTemperatureProvider;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("mock-services")
public class MockMoscowTemperatureProvider extends MoscowTemperatureProvider {
    public MockMoscowTemperatureProvider() {
        super(null);
    }

    @Override
    public double provideParameter() {
        return 15;
    }
}
