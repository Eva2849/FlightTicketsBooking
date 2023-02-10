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
                "Big",
                "Boss",
                LocalDate.of(2000, Month.JANUARY, 1),
                Gender.MALE);
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        roleRepository.save(adminRole);
        roleRepository.save(userRole);
        user.addRole(adminRole);
        user.addRole(userRole);
        userRepository.save(user);

        Flight fl1 = new Flight("LO3880", LocalDateTime.of(2023, Month.DECEMBER, 10, 12, 15), "Dnipro", "Riga", Duration.ofMinutes(80), 200);
        Flight fl2 = new Flight("W61091", LocalDateTime.of(2023, Month.DECEMBER, 10, 13, 45), "Dnipro", "London", Duration.ofMinutes(90), 80);
        Flight fl3 = new Flight("W61011", LocalDateTime.of(2023, Month.DECEMBER, 10, 14, 5), "Dnipro", "Mombasa", Duration.ofMinutes(120), 60);
        Flight fl4 = new Flight("LH1363", LocalDateTime.of(2023, Month.DECEMBER, 10, 14, 18), "Dnipro", "Reykjavik", Duration.ofMinutes(140), 20);
        Flight fl5 = new Flight("LO3883", LocalDateTime.of(2023, Month.DECEMBER, 10, 15, 6), "Dnipro", "Warsaw", Duration.ofMinutes(100), 18);
        Flight fl6 = new Flight("W61001", LocalDateTime.of(2023, Month.DECEMBER, 10, 15, 12), "Dnipro", "Frankfurt", Duration.ofMinutes(60), 60);
        Flight fl7 = new Flight("W61221", LocalDateTime.of(2023, Month.DECEMBER, 10, 15, 35), "Dnipro", "Narnia", Duration.ofMinutes(75), 5);
        Flight fl8 = new Flight("W61071", LocalDateTime.of(2023, Month.DECEMBER, 10, 15, 58), "Dnipro", "Dortmund", Duration.ofMinutes(93), 11);
        Flight fl9 = new Flight("3Z7100", LocalDateTime.of(2023, Month.DECEMBER, 10, 16, 2), "Dnipro", "London", Duration.ofMinutes(103), 34);
        Flight fl10 = new Flight("E45395", LocalDateTime.of(2023, Month.DECEMBER, 10, 16, 11), "Dnipro", "Doncaster–Sheffield", Duration.ofMinutes(67), 2);
        Flight fl11 = new Flight("E47423", LocalDateTime.of(2023, Month.DECEMBER, 10, 16, 14), "Dnipro", "Birmingham", Duration.ofMinutes(88), 2);
        Flight fl12 = new Flight("W61167", LocalDateTime.of(2023, Month.DECEMBER, 10, 16, 28), "Dnipro", "Las Palmas", Duration.ofMinutes(87), 18);
        Flight fl13 = new Flight("LO3886", LocalDateTime.of(2023, Month.DECEMBER, 10, 16, 44), "Dnipro", "RavenHolm", Duration.ofMinutes(66), 1);
        Flight fl14 = new Flight("W61005", LocalDateTime.of(2023, Month.DECEMBER, 10, 17, 0), "Dnipro", "Gdansk", Duration.ofMinutes(84), 8);
        Flight fl15 = new Flight("W91304", LocalDateTime.of(2023, Month.DECEMBER, 10, 17, 18), "Dnipro", "Moon", Duration.ofMinutes(240), 2);
        Flight fl16 = new Flight("W11322", LocalDateTime.of(2023, Month.DECEMBER, 10, 17, 38), "Dnipro", "Moon2", Duration.ofMinutes(300), 4);
        Flight fl17 = new Flight("E99307", LocalDateTime.of(2023, Month.DECEMBER, 10, 17, 51), "Dnipro", "Ishimura", Duration.ofMinutes(1000), 5);
        Flight fl18 = new Flight("7789EG", LocalDateTime.of(2023, Month.DECEMBER, 10, 18, 1), "Dnipro",  "Talos-1", Duration.ofMinutes(130), 10);
        Flight fl19 = new Flight("L88138", LocalDateTime.of(2023, Month.DECEMBER, 10, 18, 10), "Dnipro", "Liverpool", Duration.ofMinutes(75), 100);
        Flight fl20 = new Flight("X17X18", LocalDateTime.of(2023, Month.DECEMBER, 10, 18, 33), "Dnipro", "Mars", Duration.ofMinutes(666), 200);
        Flight fl21 = new Flight("W19881", LocalDateTime.of(2023, Month.DECEMBER, 10, 18, 41), "Dnipro", "Another world", Duration.ofMinutes(100), 1);


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
        flightRepository.save(fl16);
        flightRepository.save(fl17);
        flightRepository.save(fl18);
        flightRepository.save(fl19);
        flightRepository.save(fl20);
        flightRepository.save(fl21);

    }
}