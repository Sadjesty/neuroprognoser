package com.sadjesty.neuroprognoser.service;

import com.sadjesty.neuroprognoser.dto.NeuralNetworkParametersDto;
import org.jpmml.evaluator.ModelEvaluator;
import org.jpmml.evaluator.TargetField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

class PMMLModelNeuralNetworkEvaluatorServiceTest {

    public static final String TARGET_FIELD_NAME = "forecast";
    private ModelEvaluator<?> modelEvaluator =Mockito.mock(ModelEvaluator.class);
    private PMMLModelNeuralNetworkEvaluatorService pMMLModelNeuralNetworkEvaluatorService;

    @BeforeEach
    void init() {
        pMMLModelNeuralNetworkEvaluatorService = new PMMLModelNeuralNetworkEvaluatorService();
        pMMLModelNeuralNetworkEvaluatorService.setModelEvaluator(modelEvaluator);
    }

    @Test
    void testEvaluate() {
        Map mockedMap = mock(Map.class);
        TargetField targetField = mock(TargetField.class);
        double expected = 120d;

        when(targetField.getName()).thenReturn(TARGET_FIELD_NAME);
        when(modelEvaluator.getTargetFields()).thenReturn(List.of(targetField));
        when(modelEvaluator.getInputFields()).thenReturn(Collections.emptyList());
        when(modelEvaluator.evaluate(any())).thenReturn(mockedMap);
        when(mockedMap.get(TARGET_FIELD_NAME)).thenReturn(expected);

        double result = pMMLModelNeuralNetworkEvaluatorService.evaluate(NeuralNetworkParametersDto.builder().build());
        Assertions.assertEquals(expected, result);
    }
}
