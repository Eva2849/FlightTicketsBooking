package com.itstep.flightticketsbooking.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EncodedController {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/encode")
    @ResponseBody
    String encode(@RequestParam String rawPassword) {
        return rawPassword + " -> " + passwordEncoder.encode(rawPassword);
    }
}
