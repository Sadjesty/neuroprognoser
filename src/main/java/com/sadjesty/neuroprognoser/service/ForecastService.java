package com.sadjesty.neuroprognoser.service;

import com.sadjesty.neuroprognoser.dto.ForecastDto;
import com.sadjesty.neuroprognoser.entity.ForecastEntity;
import com.sadjesty.neuroprognoser.mapper.ForecastMapper;
import com.sadjesty.neuroprognoser.repository.ForecastRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForecastService {

    private final ForecastRepository forecastRepository;
    private final ForecastMapper mapper;

    public ForecastEntity saveForecast(ForecastDto forecastDto) {
        ForecastEntity forecastEntity = mapper.mapDtoToEntity(forecastDto);
        return forecastRepository.save(forecastEntity);
    }

    public List<ForecastDto> getAllForecasts() {
        return forecastRepository.findAll()
                .stream()
                .map(mapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public void updateActualOrders(Long id, int actualOrders) {
        Optional<ForecastEntity> optionalForecast = forecastRepository.findById(id);
        optionalForecast.ifPresent(forecast -> {
            forecast.setActualOrders(actualOrders);
            forecastRepository.save(forecast);
        });
    }

}
