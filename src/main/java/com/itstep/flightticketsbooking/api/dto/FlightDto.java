package com.itstep.flightticketsbooking.api.dto;

import com.itstep.flightticketsbooking.entity.Flight;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;

public class FlightDto {
    @NotNull @NotBlank
    String flightNumber;

    @NotNull @NotBlank
    LocalDateTime departureDate;

    @NotNull @NotBlank
    String origin;

    @NotNull @NotBlank
    String destination;

    @NotNull @NotBlank
    Duration estFlightDuration;

    @NotNull @NotBlank
    int maxNumSeats;

    public Flight toEntityFlight(){
        return new Flight(flightNumber,departureDate,origin,destination,estFlightDuration,maxNumSeats);
    }

}
