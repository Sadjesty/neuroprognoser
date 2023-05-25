package com.sadjesty.neuroprognoser.configuration;

import com.sadjesty.neuroprognoser.service.ParameterProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Map<Class<? extends ParameterProvider>, ParameterProvider> parameterProviders(
            List<ParameterProvider> parameterProviderList
    ) {
        return parameterProviderList.stream()
                .collect(Collectors.toMap(ParameterProvider::getClass, Function.identity()));
    }
}
