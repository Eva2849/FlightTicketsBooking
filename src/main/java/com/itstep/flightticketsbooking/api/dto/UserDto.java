package com.itstep.flightticketsbooking.api.dto;

import com.itstep.flightticketsbooking.entity.Gender;
import com.itstep.flightticketsbooking.entity.User;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class UserDto {
    @NotNull @NotBlank @Length(min = 3)
    String username;
    @NotNull @NotBlank @Length(min = 6)
    String password;
    @NotNull @NotBlank @Length(min = 3)
    String firstName;
    @NotNull @NotBlank @Length(min = 3)
    String lastName;
    @NotNull @Past
    LocalDate birthDate;
    @NotNull
    Gender gender;

    public User toEntity() {
        return new User(username, password, firstName, lastName, birthDate, gender);
    }
}
