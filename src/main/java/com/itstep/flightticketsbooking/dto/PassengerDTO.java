package com.itstep.flightticketsbooking.dto;

import com.itstep.flightticketsbooking.entity.Passenger;
import com.itstep.flightticketsbooking.entity.User;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PassengerDTO {
    private String firstName;

    private String lastName;

    private String passportData;

    private LocalDate birthDate;

    private String gender;

    private Long personId;

    public Passenger toEntity(User user) {
        String lastName = user.getLastName();
        Passenger passenger = new Passenger(firstName, lastName, passportData, birthDate, gender);

        passenger.setUser(user);
        return passenger;
    }
}
