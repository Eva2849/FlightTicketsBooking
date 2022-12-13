package com.itstep.flightticketsbooking.dto;

import com.itstep.flightticketsbooking.entity.Gender;
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

    private Gender gender;

    private Long personId;

    public Passenger toEntity() {
        return new Passenger(firstName, lastName, passportData, birthDate, gender);
    }
}
