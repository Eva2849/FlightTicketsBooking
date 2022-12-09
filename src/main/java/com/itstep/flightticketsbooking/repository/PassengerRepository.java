package com.itstep.flightticketsbooking.repository;

import com.itstep.flightticketsbooking.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

}
