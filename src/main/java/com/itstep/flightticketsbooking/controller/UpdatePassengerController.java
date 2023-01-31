package com.itstep.flightticketsbooking.controller;

import com.itstep.flightticketsbooking.api.dto.ErrorResponse;
import com.itstep.flightticketsbooking.api.dto.PassengerDto;
import com.itstep.flightticketsbooking.entity.Passenger;
import com.itstep.flightticketsbooking.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/updatePassenger")
@RequiredArgsConstructor
@Controller
public class UpdatePassengerController {
    private final PassengerRepository passengerRepository;
    @GetMapping("/{id}")
    String index(@PathVariable long id, Model model){
        model.addAttribute("passenger", passengerRepository.findById(id));
        model.addAttribute("passengerId",id);
        return "updatePassenger";
    }
    @GetMapping("/passenger/{id}")
    @ResponseBody
    public ResponseEntity<?> showPassenger(@PathVariable long id){
        Optional<Passenger> optionalPassenger = passengerRepository.findById(id);
        if(optionalPassenger.isPresent()){
            return ResponseEntity.ok(optionalPassenger.get());
        }
        return ResponseEntity.badRequest().body("Passenger not found");
    }
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updatePassenger(@PathVariable long id, @RequestBody @Validated PassengerDto passengerDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Error validation: " + bindingResult));
        }

        Passenger passenger = passengerDto.toEntity();
        Optional<Passenger> optionalPassenger = passengerRepository.findById(id);
        if(optionalPassenger.isPresent()){
            Passenger pas = optionalPassenger.get();
            pas.setFirstName(passenger.getFirstName());
            pas.setLastName(passenger.getLastName());
            pas.setPassportData(passenger.getPassportData());
            pas.setBirthDate(passenger.getBirthDate());
            pas.setGender(passenger.getGender());
            return ResponseEntity.of(Optional.of(passengerRepository.save(pas)));
        }
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("Passenger not found"));
    }
}
