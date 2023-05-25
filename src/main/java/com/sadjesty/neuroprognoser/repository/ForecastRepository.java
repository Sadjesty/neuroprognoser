package com.sadjesty.neuroprognoser.repository;

import com.sadjesty.neuroprognoser.entity.ForecastEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.Optional;

public interface ForecastRepository extends JpaRepository<ForecastEntity, Long> {

    Optional<ForecastEntity> getForecastEntityByForecastDate(Date forecastDate);

}
