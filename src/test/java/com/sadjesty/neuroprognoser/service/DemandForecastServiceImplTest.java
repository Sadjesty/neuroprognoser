package com.sadjesty.neuroprognoser.service;

import com.sadjesty.neuroprognoser.entity.ForecastEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DemandForecastServiceImplTest {

    @Mock
    private Map<Class<? extends ParameterProvider>, ParameterProvider> parameterProviders;
    @Mock
    private NeuralNetworkEvaluatorService neuralNetworkEvaluatorService;
    @Mock
    private ForecastService forecastService;
    @InjectMocks
    private DemandForecastServiceImpl demandForecastServiceImpl;

    @Test
    void testForecastDemandForTheNextDay() {
        when(neuralNetworkEvaluatorService.evaluate(any())).thenReturn(0d);
        when(forecastService.saveForecast(any())).thenReturn(new ForecastEntity());

        double result = demandForecastServiceImpl.forecastDemandForTheNextDay();
        Assertions.assertEquals(0d, result);
    }
}
