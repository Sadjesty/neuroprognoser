package com.sadjesty.neuroprognoser.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MoscowTemperatureProviderTest {

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private MoscowTemperatureProvider moscowTemperatureProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProvideParameter() {
        double result = moscowTemperatureProvider.provideParameter();
        Assertions.assertEquals(0d, result);
    }
}
