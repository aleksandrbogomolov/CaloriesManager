package com.aleksandrbogomolov.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "meals")
@CompoundIndex(name = "user_date_time", def = "{'user': 1, 'dateTime': 1}")
public class Meal implements Persistable<String> {

    @Id
    private String id;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    @Length(min = 4, max = 255)
    private String description;

    @NotNull
    @Min(value = 10)
    private int calories;

    private boolean exceed;

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
    public boolean isNew() {
        return getId().equals("");
    }
}
