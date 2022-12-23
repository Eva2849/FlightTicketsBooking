package com.itstep.flightticketsbooking.controller;

import com.itstep.flightticketsbooking.entity.Role;
import com.itstep.flightticketsbooking.repository.RoleRepository;
import com.itstep.flightticketsbooking.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @GetMapping
    String index() {
        return "registration";
    }

    @Data
    static class UsernamePassword {
        String username;
        String password;
    }

    @PostMapping
    String registration(@ModelAttribute UsernamePassword usernamePassword) {
        UserDetails userDetails = User.builder()
                .username(usernamePassword.username)
                .password(passwordEncoder.encode(usernamePassword.password))
                .roles("USER")
                .build();
//        userRepository.save(userDetails);
        return "redirect:/login";
    }
}
