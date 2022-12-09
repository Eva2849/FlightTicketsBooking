package com.itstep.flightticketsbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.itstep.flightticketsbooking.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

}

