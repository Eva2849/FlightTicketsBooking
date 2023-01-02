package com.itstep.flightticketsbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
@ToString(exclude = "users")
@NoArgsConstructor
@EqualsAndHashCode(exclude = "users")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    private String name;
    @ManyToMany
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public String getAuthority() {
        return name;
    }
}