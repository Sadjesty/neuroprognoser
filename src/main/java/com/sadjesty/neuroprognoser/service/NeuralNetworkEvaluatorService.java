package com.sadjesty.neuroprognoser.service;

import com.sadjesty.neuroprognoser.dto.NeuralNetworkParametersDto;

public interface NeuralNetworkEvaluatorService {

    double evaluate(NeuralNetworkParametersDto parametersDto);
}
