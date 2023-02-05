package com.itstep.flightticketsbooking.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@ToString(exclude = "passengers")
@EqualsAndHashCode(exclude = "passengers")
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

    @OneToMany
    @JsonIgnore
    private Set<Passenger> passengers = new HashSet<>();

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }
}
