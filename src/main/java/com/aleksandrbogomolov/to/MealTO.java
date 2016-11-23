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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealTO)) return false;

        MealTO mealTO = (MealTO) o;

        if (calories != mealTO.calories) return false;
        if (exceed != mealTO.exceed) return false;
        if (!id.equals(mealTO.id)) return false;
        if (!dateTime.equals(mealTO.dateTime)) return false;
        return description.equals(mealTO.description);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + dateTime.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + calories;
        result = 31 * result + (exceed ? 1 : 0);
        return result;
    }
}
