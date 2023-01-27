package com.itstep.flightticketsbooking.api.dto;

import com.itstep.flightticketsbooking.entity.Flight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.text.html.parser.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public static FlightDto fromEntity(Flight flight) {
        Duration flightDuration = flight.getEstFlightDuration();
        String duration = String.format("%d:%d", flightDuration.toHours(), flightDuration.toMinutesPart());
        return new FlightDto(flight.getFlightNumber(), flight.getDepartureDate(), flight.getOrigin(),
                flight.getDestination(), duration, flight.getMaxNumSeats());
    }
}
