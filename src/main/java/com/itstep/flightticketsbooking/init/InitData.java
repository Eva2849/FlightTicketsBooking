package com.itstep.flightticketsbooking.init;

import com.itstep.flightticketsbooking.entity.Flight;
import com.itstep.flightticketsbooking.entity.Gender;
import com.itstep.flightticketsbooking.entity.Role;
import com.itstep.flightticketsbooking.entity.User;
import com.itstep.flightticketsbooking.repository.FlightRepository;
import com.itstep.flightticketsbooking.repository.RoleRepository;
import com.itstep.flightticketsbooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // TODO: only for test purpose. Remove after migration to mysql
        User user = new User("admin",
                passwordEncoder.encode("admin"),
                "Maksym",
                "Shaptala",
                LocalDate.of(2000, Month.JANUARY, 1),
                Gender.MALE);
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        roleRepository.save(adminRole);
        roleRepository.save(userRole);
        user.addRole(adminRole);
        user.addRole(userRole);
        userRepository.save(user);

        Flight fl1 = new Flight("LO3880", LocalDateTime.of(2022, Month.DECEMBER, 4, 12, 15), "Dnipro", "Riga", Duration.ofMinutes(80), 200);
        Flight fl2 = new Flight("W61091", LocalDateTime.of(2022, Month.DECEMBER, 4, 13, 45), "Dnipro", "London", Duration.ofMinutes(90), 80);
        Flight fl3 = new Flight("W61011", LocalDateTime.of(2022, Month.DECEMBER, 4, 14, 5), "Dnipro", "Mombasa", Duration.ofMinutes(120), 60);
        Flight fl4 = new Flight("LH1363", LocalDateTime.of(2022, Month.DECEMBER, 4, 14, 18), "Dnipro", "Reykjavik", Duration.ofMinutes(140), 20);
        Flight fl5 = new Flight("LO3880", LocalDateTime.of(2022, Month.DECEMBER, 4, 15, 6), "Dnipro", "Warsaw", Duration.ofMinutes(100), 18);
        Flight fl6 = new Flight("W61001", LocalDateTime.of(2022, Month.DECEMBER, 4, 15, 12), "Dnipro", "Frankfurt", Duration.ofMinutes(60), 60);
        Flight fl7 = new Flight("W61221", LocalDateTime.of(2022, Month.DECEMBER, 4, 15, 35), "Dnipro", "Liverpool", Duration.ofMinutes(75), 5);
        Flight fl8 = new Flight("W61071", LocalDateTime.of(2022, Month.DECEMBER, 4, 15, 58), "Dnipro", "Dortmund", Duration.ofMinutes(93), 11);
        Flight fl9 = new Flight("3Z7100", LocalDateTime.of(2022, Month.DECEMBER, 4, 16, 2), "Dnipro", "London", Duration.ofMinutes(103), 34);
        Flight fl10 = new Flight("E45395", LocalDateTime.of(2022, Month.DECEMBER, 4, 16, 2), "Dnipro", "Doncaster–Sheffield", Duration.ofMinutes(67), 2);
        Flight fl11 = new Flight("E47423", LocalDateTime.of(2022, Month.DECEMBER, 4, 16, 14), "Dnipro", "Birmingham", Duration.ofMinutes(88), 2);
        Flight fl12 = new Flight("W61167", LocalDateTime.of(2022, Month.DECEMBER, 4, 16, 28), "Dnipro", "Las Palmas", Duration.ofMinutes(87), 18);
        Flight fl13 = new Flight("LO3886", LocalDateTime.of(2022, Month.DECEMBER, 4, 16, 44), "Dnipro", "RawenHolm", Duration.ofMinutes(66), 1);
        Flight fl14 = new Flight("W61005", LocalDateTime.of(2022, Month.DECEMBER, 4, 17, 0), "Dnipro", "Gdansk", Duration.ofMinutes(84), 2);
        Flight fl15 = new Flight("W91304", LocalDateTime.of(2022, Month.DECEMBER, 4, 17, 0), "Dnipro", "Moon", Duration.ofMinutes(240), 2);

        flightRepository.save(fl1);
        flightRepository.save(fl2);
        flightRepository.save(fl3);
        flightRepository.save(fl4);
        flightRepository.save(fl5);
        flightRepository.save(fl6);
        flightRepository.save(fl7);
        flightRepository.save(fl8);
        flightRepository.save(fl9);
        flightRepository.save(fl10);
        flightRepository.save(fl11);
        flightRepository.save(fl12);
        flightRepository.save(fl13);
        flightRepository.save(fl14);
        flightRepository.save(fl15);

        // FIXME:
        // Init this data here
        // insert into Flights(id, flightNumber, departureDate, time, origin, destination, estFlightDuration, maxNumSeats)
        //values (1, 'LO3880', '2022-12-04', '15:50', 'Dnipro', 'Riga', '01:50', 200),
        //       (2, 'W61091', '2022-12-04', '14:50', 'Dnipro', 'London', '01:50', 200),
        //       (3, 'W61011', '2022-12-04', '12:45', 'Dnipro', 'Mombasa', '01:50', 200),
        //       (4, 'LH1363', '2022-12-04', '07:50', 'Dnipro', 'Reykjavik', '01:50', 200),
        //       (5, 'LO3880', '2022-12-04', '16:50', 'Dnipro', 'Warsaw', '01:50', 200),
        //       (6, 'W61001', '2022-12-04', '15:50', 'Dnipro', 'Frankfurt', '01:50', 200),
        //       (7, 'W61221', '2022-12-04', '15:50', 'Dnipro', 'Liverpool', '01:50', 200),
        //       (8, 'W61071', '2022-12-04', '15:50', 'Dnipro', 'Dortmund', '01:50', 200),
        //       (9, '3Z7100', '2022-12-04', '14:25', 'Dnipro', 'London', '01:50', 200),
        //       (10, 'E45395', '2022-12-04', '14:45', 'Dnipro', 'Doncaster–Sheffield', '01:50', 200),
        //       (11, 'E47423', '2022-12-04', '11:40', 'Dnipro', 'Birmingham', '01:50', 200),
        //       (12, 'W61167', '2022-12-04', '12:30', 'Dnipro', 'Las Palmas', '01:50', 200),
        //       (13, 'LO3886', '2022-12-04', '13:05', 'Dnipro', 'Marsa Alam', '01:50', 200),
        //       (14, 'W61005', '2022-12-04', '09:30', 'Dnipro', 'Riga', '01:50', 200);
    }
}