package com.aleksandrbogomolov.util;

import com.aleksandrbogomolov.domain.Meal;
import com.aleksandrbogomolov.to.MealTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntityConverter {

    public static List<MealTO> getWithExceed(List<Meal> meals) {
        Map<LocalDate, Integer> map = meals.stream()
                                           .collect(Collectors.groupingBy(m -> m.getDateTime().toLocalDate(),
                                                   Collectors.summingInt(Meal::getCalories)));
        return meals
                .stream()
                .map(m -> convertToMealTO(m, map.get(m.getDateTime().toLocalDate()) > 2000)).collect(Collectors.toList());
    }

    public static MealTO convertToMealTO(Meal meal, boolean exceed) {
        return new MealTO(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceed);
    }
}
