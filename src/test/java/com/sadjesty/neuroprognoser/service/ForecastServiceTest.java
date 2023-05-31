package com.sadjesty.neuroprognoser.service;

import com.sadjesty.neuroprognoser.dto.ForecastDto;
import com.sadjesty.neuroprognoser.entity.ForecastEntity;
import com.sadjesty.neuroprognoser.mapper.ForecastMapper;
import com.sadjesty.neuroprognoser.repository.ForecastRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ForecastServiceTest {

    @Mock
    private ForecastRepository forecastRepository;
    @Mock
    private ForecastMapper mapper;
    @InjectMocks
    private ForecastService forecastService;

    @Test
    void testSaveForecast() {
        when(mapper.mapDtoToEntity(any())).thenReturn(new ForecastEntity());
        when(forecastRepository.save(any(ForecastEntity.class))).thenReturn(new ForecastEntity());

        ForecastEntity result = forecastService.saveForecast(null);
        Assertions.assertEquals(new ForecastEntity(), result);
    }

    @Test
    void testGetAllForecasts() {
        when(forecastRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
        List<ForecastDto> result = forecastService.getAllForecasts();
        Assertions.assertEquals(Collections.EMPTY_LIST, result);
    }

    @Test
    void testUpdateActualOrders() {
        forecastService.updateActualOrders(Long.valueOf(1), 0);
    }
}
