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

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Properties;

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
        Role adminRole = new Role("ADMIN");
        roleRepository.save(adminRole);
        user.addRole(adminRole);
        userRepository.save(user);

        Flight fl1 = new Flight("LO3880", LocalDateTime.of(2022, Month.DECEMBER, 4, 0, 0), "Dnipro", "Riga", Duration.ofHours(1), 200);
        Flight fl2 = new Flight("W61091", LocalDateTime.of(2022, Month.DECEMBER, 4, 0, 0), "Dnipro", "London", Duration.ofHours(1), 80);
        Flight fl3 = new Flight("W61011", LocalDateTime.of(2022, Month.DECEMBER, 4, 0, 0), "Dnipro", "Mombasa", Duration.ofHours(1), 60);

        flightRepository.save(fl1);
        flightRepository.save(fl2);
        flightRepository.save(fl3);

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