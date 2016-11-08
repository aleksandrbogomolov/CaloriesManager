package com.aleksandrbogomolov.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
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
}
