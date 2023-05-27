package com.sadjesty.neuroprognoser.service;

import com.sadjesty.neuroprognoser.dto.NeuralNetworkParametersDto;
import jakarta.xml.bind.JAXBException;
import org.jpmml.evaluator.FieldValue;
import org.jpmml.evaluator.InputField;
import org.jpmml.evaluator.LoadingModelEvaluatorBuilder;
import org.jpmml.evaluator.ModelEvaluator;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Profile("dev")
public class PMMLModelNeuralNetworkEvaluatorService implements NeuralNetworkEvaluatorService {

    private ModelEvaluator<?> modelEvaluator;

    public PMMLModelNeuralNetworkEvaluatorService() throws Exception {
        // Загрузка модели PMML из файла в ресурсах
        ClassPathResource resource = new ClassPathResource("model.pmml");
        // Создание экземпляра ModelEvaluator с использованием LoadingModelEvaluatorBuilder
        modelEvaluator = new LoadingModelEvaluatorBuilder()
                .load(resource.getInputStream())
                .build();

        // Проверка, что модель имеет ровно одно целевое поле
        if (modelEvaluator.getTargetFields().size() != 1) {
            throw new IllegalArgumentException("Модель должна иметь только одно целевое поле");
        }
    }

    public double evaluate(NeuralNetworkParametersDto parametersDto) {
        // Создание карты значений входных полей модели
        String targetFieldName = modelEvaluator.getTargetFields().get(0).getName();
        Map<String, FieldValue> inputFieldValues = new LinkedHashMap<>();
        List<? extends InputField> inputFields = modelEvaluator.getInputFields();
        Map<String, Double> nameAndValueCoeficientMap = parametersDto.getNameAndValueCoeficientMap();
        for (InputField inputField : inputFields) {
            String inputFieldName = inputField.getName();
            FieldValue inputValue = inputField.prepare(nameAndValueCoeficientMap.get(inputFieldName));
            inputFieldValues.put(inputFieldName, inputValue);
        }

        // Оценка модели и получение результата
        Map<String, ?> resultMap = modelEvaluator.evaluate(inputFieldValues);
        Object targetFieldValue = resultMap.get(targetFieldName);

        // Обработка результата (может потребоваться преобразование типов или другие манипуляции)
        double output = (Double) targetFieldValue;

        return output;
    }
}
