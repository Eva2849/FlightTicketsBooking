package com.itstep.flightticketsbooking.api.controller;

import com.itstep.flightticketsbooking.api.dto.ErrorResponse;
import com.itstep.flightticketsbooking.api.dto.FlightDto;
import com.itstep.flightticketsbooking.api.dto.UserDto;
import com.itstep.flightticketsbooking.entity.Flight;
import com.itstep.flightticketsbooking.entity.User;
import com.itstep.flightticketsbooking.repository.FlightRepository;
import com.itstep.flightticketsbooking.repository.RoleRepository;
import com.itstep.flightticketsbooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/admin")
@RestController
@RequiredArgsConstructor
public class AdminRestController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final FlightRepository flightRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> showUser(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        }
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("User not found"));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("User not found"));
    }

    @DeleteMapping("/flights/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);
        if (optionalFlight.isPresent()) {
            flightRepository.delete(optionalFlight.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("Flight not found"));
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

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody @Validated UserDto userDto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            User user = userDto.toEntity();
            try {
                user.addRole(roleRepository.findByName("ROLE_USER"));
                user = userRepository.save(user);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ErrorResponse(e.getMessage()));
            }
            URI uri = URI.create("/api/v1/admin/users/" + user.getId());
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

    @PostMapping("/flights")
    public ResponseEntity<?> createFlight(@RequestBody @Validated FlightDto flightDto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            Flight flight = flightDto.toEntityFlight();
            try {
                flight = flightRepository.save(flight);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
            }
            URI uri = URI.create("/api/v1/admin/flights/" + flight.getId());
            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }
}
