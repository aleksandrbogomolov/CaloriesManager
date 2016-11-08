package com.aleksandrbogomolov.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String name;

    @Indexed(unique = true)
    private String email;

    private String password;

    private int caloriesPerDay;

    private boolean enabled;

    private LocalDate createdDate;

    private Set<Role> roles;

//    public User(String name, String email, String password, int caloriesPerDay, boolean enabled, LocalDate createdDate, Set<Role> roles) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.caloriesPerDay = caloriesPerDay;
//        this.enabled = enabled;
//        this.createdDate = createdDate;
//        this.roles = roles;
//    }

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
