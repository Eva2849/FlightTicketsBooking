package com.itstep.flightticketsbooking.api.dto;

import com.itstep.flightticketsbooking.entity.Flight;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
public class FlightDto {
    public static final String TIME_REGEX = "(\\d+):(\\d+)";
    public static final String DURATION_REPLACEMENT = "PT$1H$2M";
    @NotNull @NotBlank
    private String flightNumber;

    @NotNull
    private LocalDateTime departureDate;

    @NotNull @NotBlank
    private String origin;

    @NotNull @NotBlank
    private String destination;

    @NotNull @NotBlank
    private String estFlightDuration;

    @NotNull
    private int maxNumSeats;

    public Flight toEntityFlight() {
        String duration = estFlightDuration.replaceFirst(TIME_REGEX, DURATION_REPLACEMENT);
        return new Flight(flightNumber, departureDate, origin, destination, Duration.parse(duration), maxNumSeats);
    }
}
