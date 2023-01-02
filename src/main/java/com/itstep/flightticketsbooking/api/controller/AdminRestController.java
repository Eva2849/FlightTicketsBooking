package com.itstep.flightticketsbooking.api.controller;

import com.itstep.flightticketsbooking.api.dto.ErrorResponse;
import com.itstep.flightticketsbooking.api.dto.UserDto;
import com.itstep.flightticketsbooking.entity.User;
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

    @GetMapping("/users")
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> showUser(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        }
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("User not found"));
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
        throw new RuntimeException("Not implemented yet");
    }
}
