package com.itstep.flightticketsbooking.entity;

import javax.persistence.*;

import lombok.*;

import java.sql.Time;
import java.time.LocalDate;

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
    private LocalDate departureDate;

    @NonNull
    @Column(name = "time")
    private Time time;

    @NonNull
    @Column(name = "origin")
    private String origin;

    @NonNull
    @Column(name = "destination")
    private String destination;

    @NonNull
    @Column(name = "estFlightDuration")
    private Time estFlightDuration;

    @NonNull
    @Column(name = "maxNumSeats")
    private int maxNumSeats;

    @ManyToOne
    private User user;
}
