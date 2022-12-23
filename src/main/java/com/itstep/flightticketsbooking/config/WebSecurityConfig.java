package com.itstep.flightticketsbooking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/", "/h2-console/flights", "/registration").permitAll()
                        .antMatchers("/adminPage/**").hasRole("ADMIN")
                        .antMatchers("/userPage/**").hasRole("USER")
                        .anyRequest().denyAll())
                .formLogin().successHandler((request, response, authentication) -> {
                    if (authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().contains("ADMIN"))) {
                        response.sendRedirect("/adminPage");
                    } else {
                        response.sendRedirect("/userPage");
                    }
                })
                .and()
                .build();
    }
}

