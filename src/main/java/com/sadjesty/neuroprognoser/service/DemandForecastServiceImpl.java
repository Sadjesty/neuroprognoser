package com.sadjesty.neuroprognoser.service;

import com.sadjesty.neuroprognoser.dto.ForecastDto;
import com.sadjesty.neuroprognoser.dto.NeuralNetworkParametersDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class DemandForecastServiceImpl implements DemandForecastService {

    private final Map<Class<? extends ParameterProvider>, ParameterProvider> parameterProviders;
    private final NeuralNetworkEvaluatorService neuralNetworkEvaluatorService;
    private final ForecastService forecastService;

    public DemandForecastServiceImpl(Map<Class<? extends ParameterProvider>, ParameterProvider> parameterProviders,
                                     NeuralNetworkEvaluatorService neuralNetworkEvaluatorService,
                                     ForecastService forecastService) {
        this.parameterProviders = parameterProviders;
        this.neuralNetworkEvaluatorService = neuralNetworkEvaluatorService;
        this.forecastService = forecastService;
    }

    @Override
    public double forecastDemandForTheNextDay() {
        double temperatureCoefficient = getCoefficient(MoscowTemperatureProvider.class);
        double currencyCoefficient = getCoefficient(CurrencyExchangeRateProvider.class);
        double holidayCoefficient = getCoefficient(HolidayCoefficientProvider.class);
        double paydayCoefficient = getCoefficient(PaydayCoefficientProvider.class);
        NeuralNetworkParametersDto neuralNetworkParametersDto = getNeuralNetworkParametersDto(
                temperatureCoefficient, currencyCoefficient, holidayCoefficient, paydayCoefficient
        );

        double forecast = neuralNetworkEvaluatorService.evaluate(neuralNetworkParametersDto);

        saveForecast(
                temperatureCoefficient, currencyCoefficient, holidayCoefficient, paydayCoefficient, (int) forecast
        );

        return forecast;
    }

    private double getCoefficient(Class<? extends ParameterProvider> providerType) {
        ParameterProvider provider = parameterProviders.get(providerType);
        if (provider != null) {
            return provider.provideParameter();
        }
        return 0.0;
    }

    private NeuralNetworkParametersDto getNeuralNetworkParametersDto(
            double temperatureCoefficient,
            double currencyCoefficient,
            double holidayCoefficient,
            double paydayCoefficient
    ) {
        return NeuralNetworkParametersDto.builder()
                .temperatureCoefficient(temperatureCoefficient)
                .currencyCoefficient(currencyCoefficient)
                .holidayCoefficient(holidayCoefficient)
                .paydayCoefficient(paydayCoefficient)
                .build();
    }

    private void saveForecast(
            double temperatureCoefficient,
            double currencyCoefficient,
            double holidayCoefficient,
            double paydayCoefficient,
            int forecast
    ) {
        forecastService.saveForecast(ForecastDto.builder()
                .forecastDate(LocalDate.now())
                .temperature(temperatureCoefficient)
                .exchangeRate(currencyCoefficient)
                .holidayCoefficient(holidayCoefficient)
                .paydayCoefficient(paydayCoefficient)
                .predictedOrders(forecast)
                .build());
    }
}
