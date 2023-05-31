package com.sadjesty.neuroprognoser.service;

import com.sadjesty.neuroprognoser.dto.NeuralNetworkParametersDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jpmml.evaluator.FieldValue;
import org.jpmml.evaluator.InputField;
import org.jpmml.evaluator.LoadingModelEvaluatorBuilder;
import org.jpmml.evaluator.ModelEvaluator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Profile("dev")
@Data
@NoArgsConstructor
public class PMMLModelNeuralNetworkEvaluatorService implements NeuralNetworkEvaluatorService {

    private ModelEvaluator<?> modelEvaluator;
    public PMMLModelNeuralNetworkEvaluatorService(@Value("app.forecast.currency.modelpath") String pathToModel) throws Exception {
        // Get PMML model from resources
        ClassPathResource resource = new ClassPathResource(pathToModel);
        // Creation of ModelEvaluator using a LoadingModelEvaluatorBuilder
        modelEvaluator = new LoadingModelEvaluatorBuilder()
                .load(resource.getInputStream())
                .build();
    }

    public double evaluate(NeuralNetworkParametersDto parametersDto) {
        // Map of model fields initialization
        String targetFieldName = modelEvaluator.getTargetFields().get(0).getName();
        Map<String, FieldValue> inputFieldValues = new LinkedHashMap<>();
        List<? extends InputField> inputFields = modelEvaluator.getInputFields();
        Map<String, Double> nameAndValueCoeficientMap = parametersDto.getNameAndValueCoeficientMap();
        for (InputField inputField : inputFields) {
            String inputFieldName = inputField.getName();
            FieldValue inputValue = inputField.prepare(nameAndValueCoeficientMap.get(inputFieldName));
            inputFieldValues.put(inputFieldName, inputValue);
        }

        // Getting the result
        Map<String, ?> resultMap = modelEvaluator.evaluate(inputFieldValues);
        Object targetFieldValue = resultMap.get(targetFieldName);
        double output = (Double) targetFieldValue;

        return output;
    }
}
