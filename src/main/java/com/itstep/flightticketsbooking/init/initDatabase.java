package com.itstep.flightticketsbooking.init;

import com.itstep.flightticketsbooking.entity.Flight;
import com.itstep.flightticketsbooking.entity.User;
import com.itstep.flightticketsbooking.repository.FlightRepository;
import com.itstep.flightticketsbooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class initDatabase implements CommandLineRunner {

    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = userRepository.save(new User("artLana", "12345", "Lana", "Art", LocalDate.of(1987, 3, 14), "female"));

        Flight flight = new Flight("123", LocalDate.of(2023, 9, 10), Time.valueOf("10:00"), "Dnipro", "Kyev", Time.valueOf("1:50"), 150);
        user.addFlight(flight);
        flightRepository.save(flight);
    }
}

