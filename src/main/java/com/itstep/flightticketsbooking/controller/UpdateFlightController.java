package com.itstep.flightticketsbooking.controller;

import com.itstep.flightticketsbooking.api.dto.ErrorResponse;
import com.itstep.flightticketsbooking.entity.Flight;
import com.itstep.flightticketsbooking.entity.User;
import com.itstep.flightticketsbooking.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/updateFlight")
@RestController
@RequiredArgsConstructor
public class UpdateFlightController {
    private final FlightRepository flightRepository;
//    @GetMapping
//    String index(Model model) {
//        model.addAttribute("flight", showFlight(id));
//        return "registration";
//    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<?> showFlight(@PathVariable Long id) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);
        if (optionalFlight.isPresent()) {
            return ResponseEntity.ok(optionalFlight.get());
        }
        return ResponseEntity.badRequest().body("Flight not found");
    }

    @PutMapping("/flights/{id}")
    public ResponseEntity<?> updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);
        if (optionalFlight.isPresent()) {
            Flight fl = optionalFlight.get();
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
