package com.aleksandrbogomolov.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "meals")
@CompoundIndex(name = "user_date_time", def = "{'user': 1, 'dateTime': 1}")
public class Meal {

    @Id
    private String id;

    private LocalDateTime dateTime;

    private String description;

    private int calories;

    @DBRef
    private User user;

    @Override
    public String toString() {
        return "Meal{" +
               "id='" + id + '\'' +
               ", dateTime=" + dateTime +
               ", description='" + description + '\'' +
               ", calories=" + calories +
               ", user=" + user +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meal)) return false;

        Meal meal = (Meal) o;

        if (calories != meal.calories) return false;
        if (id != null ? !id.equals(meal.id) : meal.id != null) return false;
        if (dateTime != null ? !dateTime.equals(meal.dateTime) : meal.dateTime != null) return false;
        if (description != null ? !description.equals(meal.description) : meal.description != null) return false;
        return user != null ? user.equals(meal.user) : meal.user == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + calories;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
