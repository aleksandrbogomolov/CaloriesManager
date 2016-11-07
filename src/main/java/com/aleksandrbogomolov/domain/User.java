package com.aleksandrbogomolov.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String name;

    private String email;

    private String password;

    private boolean enabled;

    private LocalDate createdDate;

    private Set<Role> roles;

    public User(String name, String email, String password, boolean enabled, LocalDate createdDate, Set<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.createdDate = createdDate;
        this.roles = roles;
    }

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
