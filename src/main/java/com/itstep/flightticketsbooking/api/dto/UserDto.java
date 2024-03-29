package com.itstep.flightticketsbooking.api.dto;

import com.itstep.flightticketsbooking.entity.Gender;
import com.itstep.flightticketsbooking.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotNull @NotBlank @Length(min = 3)
    private String username;
    @NotNull @NotBlank @Length(min = 6)
    private String password;
    @NotNull @NotBlank @Length(min = 3)
    private String firstName;
    @NotNull @NotBlank @Length(min = 3)
    private String lastName;
    @NotNull @Past
    private LocalDate birthDate;
    @NotNull
    private Gender gender;

    public User toEntity() {
        return new User(username, password, firstName, lastName, birthDate, gender);
    }
}
