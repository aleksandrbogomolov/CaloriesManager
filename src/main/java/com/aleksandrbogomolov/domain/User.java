package com.aleksandrbogomolov.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class User {

    @Id
    private String id;

    private String name;

    private String email;

    private String password;

    private boolean enabled;

    private LocalDate createdDate;

    private Set<Role> roles;

    @Override
    public String toString() {
        return "User{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               ", password='" + password + '\'' +
               ", enabled=" + enabled +
               ", createdDate=" + createdDate +
               ", roles=" + roles +
               '}';
    }
}
