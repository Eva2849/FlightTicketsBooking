package com.itstep.flightticketsbooking.controller;

import com.itstep.flightticketsbooking.api.dto.ErrorResponse;
import com.itstep.flightticketsbooking.api.dto.FlightDto;
import com.itstep.flightticketsbooking.entity.Flight;
import com.itstep.flightticketsbooking.entity.User;
import com.itstep.flightticketsbooking.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/updateFlight")
@RequiredArgsConstructor
@Controller
public class UpdateFlightController {
    private final FlightRepository flightRepository;

    @GetMapping("/{id}")
    String index(@PathVariable long id, Model model) {
        model.addAttribute("flight", FlightDto.fromEntity(flightRepository.findById(id).orElseThrow()));
        model.addAttribute("flightId", id);
        return "updateFlight";
    }

    @GetMapping("/flights/{id}")
    @ResponseBody
    public ResponseEntity<?> showFlight(@PathVariable Long id) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);
        if (optionalFlight.isPresent()) {
            return ResponseEntity.ok(optionalFlight.get());
        }
        return ResponseEntity.badRequest().body("Flight not found");
    }

    @PutMapping("/flights/{id}")
    @ResponseBody
    public ResponseEntity<?> updateFlight(@PathVariable Long id, @RequestBody @Validated FlightDto flightDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Error validation: " + bindingResult));
        }

        Optional<Flight> optionalFlight = flightRepository.findById(id);

        if (optionalFlight.isPresent()) {
            Flight fl = optionalFlight.get();
            Flight flight = flightDto.toEntityFlight();

            fl.setFlightNumber(flight.getFlightNumber());
            fl.setDepartureDate(flight.getDepartureDate());
            fl.setOrigin(flight.getOrigin());
            fl.setDestination(flight.getDestination());
            fl.setEstFlightDuration(flight.getEstFlightDuration());
            fl.setMaxNumSeats(flight.getMaxNumSeats());

            return ResponseEntity.of(Optional.of(flightRepository.save(fl)));
        }
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("Flight not found"));
    }
}
