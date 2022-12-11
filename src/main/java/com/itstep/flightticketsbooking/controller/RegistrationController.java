package com.itstep.flightticketsbooking.controller;

import com.itstep.flightticketsbooking.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/registration") //регистрация
@RequiredArgsConstructor
public class RegistrationController {

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
        // <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"/> обязательное поле для Post запроса
    String registration(@ModelAttribute com.itstep.flightticketsbooking.controller.RegistrationController.UsernamePassword usernamePassword) {
        throw new RuntimeException("Not implemented yet");
//        return "redirect:/login";
    }
}
