package com.aleksandrbogomolov.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class UserMeal {

    @Id
    private String id;

    private LocalDateTime dateTime;

    private String description;

    private Integer calories;

    private User user;

    @Override
    public String toString() {
        return "UserMeal{" +
               "id='" + id + '\'' +
               ", dateTime=" + dateTime +
               ", description='" + description + '\'' +
               ", calories=" + calories +
               ", user=" + user +
               '}';
    }
}
