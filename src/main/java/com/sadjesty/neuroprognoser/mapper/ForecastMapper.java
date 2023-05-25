package com.sadjesty.neuroprognoser.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sadjesty.neuroprognoser.dto.ForecastDto;
import com.sadjesty.neuroprognoser.entity.ForecastEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@RequiredArgsConstructor
public class ForecastMapper {

    private final ObjectMapper objectMapper;

    public ForecastDto mapEntityToDto(ForecastEntity entity) {
        ForecastDto forecastDto = objectMapper.convertValue(entity, ForecastDto.class);
        forecastDto.setForecastDate(entity.getForecastDate().toLocalDate());
        return forecastDto;
    }

    public ForecastEntity mapDtoToEntity(ForecastDto dto) {
        ForecastEntity forecastEntity = objectMapper.convertValue(dto, ForecastEntity.class);
        forecastEntity.setForecastDate(Date.valueOf(dto.getForecastDate()));
        return forecastEntity;
    }
}
