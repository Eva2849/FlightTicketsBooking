package com.itstep.flightticketsbooking.repository;

import com.itstep.flightticketsbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}