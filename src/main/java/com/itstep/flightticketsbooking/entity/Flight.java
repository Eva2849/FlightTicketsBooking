package com.itstep.flightticketsbooking.entity;

import javax.persistence.*;

import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Table(name = "Flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(name = "flightNumber")
    private String flightNumber;

    @NonNull
    @Column(name = "departureDate")
    private LocalDateTime departureDate;

    @NonNull
    @Column(name = "origin")
    private String origin;

    @NonNull
    @Column(name = "destination")
    private String destination;

    @NonNull
    @Column(name = "estFlightDuration")
    private Duration estFlightDuration;

    @NonNull
    @Column(name = "maxNumSeats")
    private int maxNumSeats;
}
