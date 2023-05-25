package com.sadjesty.neuroprognoser.service;

import com.sadjesty.neuroprognoser.dto.NeuralNetworkParametersDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DemandForecastServiceImpl implements DemandForecastService {

    private final Map<Class<? extends ParameterProvider>, ParameterProvider> parameterProviders;
    private final NeuralNetworkEvaluatorService neuralNetworkEvaluatorService;

    public DemandForecastServiceImpl(Map<Class<? extends ParameterProvider>, ParameterProvider> parameterProviders,
                                     NeuralNetworkEvaluatorService neuralNetworkEvaluatorService) {
        this.parameterProviders = parameterProviders;
        this.neuralNetworkEvaluatorService = neuralNetworkEvaluatorService;
    }

    @Override
    public double forecastDemandForTheNextDay() {
        /*NeuralNetworkParametersDto neuralNetworkParametersDto = NeuralNetworkParametersDto.builder()
                .temperatureCoefficient(getCoefficient(MoscowTemperatureProvider.class))
                .currencyCoefficient(getCoefficient(CurrencyExchangeRateProvider.class))
                .holidayCoefficient(getCoefficient(HolidayCoefficientProvider.class))
                .salaryCoefficient(getCoefficient(SalaryCoefficientProvider.class))
                .build();

        return neuralNetworkEvaluatorService.evaluate(neuralNetworkParametersDto);*/

        return 0.00;
    }

    private double getCoefficient(Class<? extends ParameterProvider> providerType) {
        ParameterProvider provider = parameterProviders.get(providerType);
        if (provider != null) {
            return provider.provideParameter();
        }
        return 0.0;
    }
}
