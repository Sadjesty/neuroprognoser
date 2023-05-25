package com.sadjesty.neuroprognoser.controller;

import com.sadjesty.neuroprognoser.service.DemandForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DemandForecastController {

    private final DemandForecastService demandForecastService;

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
}
