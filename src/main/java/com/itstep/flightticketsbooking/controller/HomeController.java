package com.itstep.flightticketsbooking.controller;

import com.itstep.flightticketsbooking.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final FlightRepository flightRepository;

    @GetMapping
    String homePage(Model model) {
        model.addAttribute("flights", flightRepository.findAll());
        return "homePage";
    }
}