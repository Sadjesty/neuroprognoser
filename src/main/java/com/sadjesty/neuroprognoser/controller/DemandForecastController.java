package com.sadjesty.neuroprognoser.controller;

import com.sadjesty.neuroprognoser.dto.ForecastDto;
import com.sadjesty.neuroprognoser.entity.ForecastEntity;
import com.sadjesty.neuroprognoser.service.DemandForecastService;
import com.sadjesty.neuroprognoser.service.ForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DemandForecastController {

    private final DemandForecastService demandForecastService;
    private final ForecastService forecastService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/forecast")
    public String getForecast(Model model) {
        double forecastedOrders = demandForecastService.forecastDemandForTheNextDay();
        model.addAttribute("forecast", forecastedOrders);
        return "forecast";
    }

    @GetMapping("/forecasts")
    public String getAllForecasts(Model model) {
        List<ForecastDto> forecasts = forecastService.getAllForecasts();
        model.addAttribute("forecasts", forecasts);
        return "forecasts";
    }

    @PutMapping("/forecasts/{id}")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public ResponseEntity<String> updateForecast(@PathVariable("id") Long id, @RequestParam("actualOrders") String actualOrders) {
        forecastService.updateActualOrders(id, Integer.parseInt(actualOrders));
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("{}");
    }

}
