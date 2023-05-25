package com.sadjesty.neuroprognoser.dto;

import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class NeuralNetworkParametersDto {

    private double temperatureCoefficient;
    private double currencyCoefficient;
    private double holidayCoefficient;
    private double salaryCoefficient;

    public Map<String, Double> getNameAndValueCoeficientMap() {
        Map<String, Double> parameters = new HashMap<>();
        for (Field declaredField : this.getClass().getDeclaredFields()) {
            declaredField.setAccessible(true);
            try {
                parameters.put(declaredField.getName(), (Double) declaredField.get(this));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return parameters;
    }
}
