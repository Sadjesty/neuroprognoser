package com.sadjesty.neuroprognoser.service.mock;

import com.sadjesty.neuroprognoser.dto.NeuralNetworkParametersDto;
import com.sadjesty.neuroprognoser.service.NeuralNetworkEvaluatorService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("mock-services")
public class MockNeuralNetworkService implements NeuralNetworkEvaluatorService {
    @Override
    public double evaluate(NeuralNetworkParametersDto parametersDto) {
        return 120;
    }
}
