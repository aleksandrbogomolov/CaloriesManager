package com.aleksandrbogomolov.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
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

    private int caloriesPerDay;

    private boolean enabled;

    private LocalDate createdDate;

    private Set<Role> roles;

    public User(String name, String email, String password, int caloriesPerDay, boolean enabled, LocalDate createdDate, Set<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.caloriesPerDay = caloriesPerDay;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (caloriesPerDay != user.caloriesPerDay) return false;
        if (!name.equals(user.name)) return false;
        if (!email.equals(user.email)) return false;
        if (!password.equals(user.password)) return false;
        if (!createdDate.equals(user.createdDate)) return false;
        return roles.equals(user.roles);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + caloriesPerDay;
        result = 31 * result + createdDate.hashCode();
        result = 31 * result + roles.hashCode();
        return result;
    }
}
