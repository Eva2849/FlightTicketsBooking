package com.itstep.flightticketsbooking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
                        .mvcMatchers("/", "/registration").permitAll()
//                        .mvcMatchers("/privatePage/**").authenticated()
                        .mvcMatchers("/adminPage/**").hasRole("ADMIN")
                        .mvcMatchers("/userPage/**").hasRole("USER")
                        .anyRequest().denyAll())
                .formLogin()
                .and()
                .build();
    }


}

