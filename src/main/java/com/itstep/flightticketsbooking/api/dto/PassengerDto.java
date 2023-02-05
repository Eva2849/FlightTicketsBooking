package com.itstep.flightticketsbooking.api.dto;

import com.itstep.flightticketsbooking.entity.Flight;
import com.itstep.flightticketsbooking.entity.Gender;
import com.itstep.flightticketsbooking.entity.Passenger;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class PassengerDto {
    @NotNull @NotBlank @Length(min = 3)
    String firstName;
    @NotNull @NotBlank @Length(min = 3)
    String lastName;
    @NotNull @NotBlank
    String passportData;
    @NotNull @Past
    LocalDate birthDate;
    @NotNull
    Gender gender;
    @NotNull
    String flightNumber;

    public Passenger toEntity(Flight flight) {
        Passenger passenger = new Passenger(firstName, lastName, passportData, birthDate, gender);
        passenger.setFlight(flight);
        return passenger;
    }
}
