package com.itstep.flightticketsbooking.api.dto;

import com.itstep.flightticketsbooking.entity.Flight;
import com.itstep.flightticketsbooking.entity.Gender;
import com.itstep.flightticketsbooking.entity.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {
    @NotNull @NotBlank @Length(min = 3)
    private String firstName;
    @NotNull @NotBlank @Length(min = 3)
    private String lastName;
    @NotNull @NotBlank
    private String passportData;
    @NotNull @Past
    private LocalDate birthDate;
    @NotNull
    private Gender gender;
    @NotNull @NotBlank
    private String flightNumber;

    public Passenger toEntity(Flight flight) {
        Passenger passenger = new Passenger(firstName, lastName, passportData, birthDate, gender);
        passenger.setFlight(flight);
        return passenger;
    }
}
