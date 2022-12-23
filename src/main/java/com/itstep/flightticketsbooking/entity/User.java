package com.itstep.flightticketsbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
@ToString(exclude = "flights")
@EqualsAndHashCode(exclude = {"roles", "flights"})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @NotBlank
    @NotNull
    @Column(unique = true)
    private String username;

    @NonNull
    @NotBlank
    @NotNull
    private String password;

    @NonNull
    @NotBlank
    @NotNull
    private String firstName;

    @NonNull
    @NotBlank
    @NotNull
    private String lastName;

    @NonNull
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private LocalDate birthDate;

    @NonNull
    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Flight> flights = new ArrayList<>();

    public void addFlight(Flight flight) {
        flight.setUser(this);
        flights.add(flight);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addRole(Role role) {
        role.addUser(this);
        roles.add(role);
    }
}