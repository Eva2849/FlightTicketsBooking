package com.itstep.flightticketsbooking.api.controller;

import com.itstep.flightticketsbooking.api.dto.ErrorResponse;
import com.itstep.flightticketsbooking.api.dto.PassengerDto;
import com.itstep.flightticketsbooking.entity.Flight;
import com.itstep.flightticketsbooking.entity.Passenger;
import com.itstep.flightticketsbooking.repository.FlightRepository;
import com.itstep.flightticketsbooking.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/user")
@RestController
@RequiredArgsConstructor
public class UserRestController {
    private final PassengerRepository passengerRepository;
    private final FlightRepository flightRepository;

    @GetMapping("/passengers")
    public ResponseEntity<List<Passenger>> listPassengers() {
        return ResponseEntity.ok(passengerRepository.findAll());
    }

    @GetMapping("/passengers/{id}")
    public ResponseEntity<?> showPassenger(@PathVariable Long id) {
        Optional<Passenger> optionalPassenger = passengerRepository.findById(id);
        if (optionalPassenger.isPresent()) {
            return ResponseEntity.ok(optionalPassenger.get());
        }
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("User not found"));
    }

    @PostMapping("/passengers")
    public ResponseEntity<?> createPassenger(@RequestBody @Validated PassengerDto passengerDto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            Passenger passenger = passengerDto.toEntity();
            try {
                passenger = passengerRepository.save(passenger);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ErrorResponse(e.getMessage()));
            }
            URI uri = URI.create("/api/v1/user/passengers/" + passenger.getId());
            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.badRequest()
                .body(bindingResult.getAllErrors());
    }

    @GetMapping("/flights")
    public ResponseEntity<?> listFlights() {
        return ResponseEntity.ok(flightRepository.findAll());
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<?> showFlight(@PathVariable Long id) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);
        if (optionalFlight.isPresent()) {
            return ResponseEntity.ok(optionalFlight.get());
        }
        return ResponseEntity.badRequest().body("Flight not found");
    }
}
