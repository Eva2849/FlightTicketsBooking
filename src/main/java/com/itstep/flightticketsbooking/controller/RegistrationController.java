package com.itstep.flightticketsbooking.controller;

import com.itstep.flightticketsbooking.entity.Gender;
import com.itstep.flightticketsbooking.entity.Role;
import com.itstep.flightticketsbooking.entity.User;
import com.itstep.flightticketsbooking.repository.RoleRepository;
import com.itstep.flightticketsbooking.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @GetMapping
    String index(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping
    String registration(@Validated @ModelAttribute User user, BindingResult bindingResult, Model redirectAttributes) {
        if (!bindingResult.hasErrors()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.addRole(roleRepository.findByName("ROLE_USER"));
            userRepository.save(user);
            return "redirect:/login";
        }
        redirectAttributes.addAttribute("errors", bindingResult.getFieldErrors());
        redirectAttributes.addAttribute("user", user);
        return "/registration";
    }
}
