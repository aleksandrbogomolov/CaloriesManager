package com.aleksandrbogomolov.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MealTO {

    private String id;

    private LocalDateTime dateTime;

    private String description;

    private int calories;

    private boolean exceed;
}
