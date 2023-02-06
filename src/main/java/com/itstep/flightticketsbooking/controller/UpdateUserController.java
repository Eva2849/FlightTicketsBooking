package com.itstep.flightticketsbooking.controller;

import com.itstep.flightticketsbooking.api.dto.ErrorResponse;
import com.itstep.flightticketsbooking.api.dto.UserDto;
import com.itstep.flightticketsbooking.entity.User;
import com.itstep.flightticketsbooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/updateUser")
@RequiredArgsConstructor
@Controller
public class UpdateUserController {
    private final UserRepository userRepository;
    @GetMapping("/{id}")
    String index(@PathVariable long id, Model model){
        model.addAttribute("user", userRepository.findById(id).orElseThrow());
        model.addAttribute("userId", id);
        return "updateUser";
    }
    @GetMapping("/users/{id}")
    @ResponseBody
    public ResponseEntity<?> showUser(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        }
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("User not found"));
    }
    @PutMapping("/users/{id}")
    @ResponseBody
    public ResponseEntity<?> updateUser(@PathVariable Long id , @RequestBody @Validated UserDto userDto , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(new ErrorResponse("Error validation: " + bindingResult));
        }

        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()){
            User us = optionalUser.get();
            User user = userDto.toEntity();

            us.setUsername(user.getUsername());
            us.setPassword(user.getPassword());
            us.setFirstName(user.getFirstName());
            us.setLastName(user.getLastName());
            us.setBirthDate(user.getBirthDate());
            us.setGender(user.getGender());
            return ResponseEntity.of(Optional.of(userRepository.save(us)));
        }
        return ResponseEntity.badRequest().body(new ErrorResponse("User not found"));
    }
}
