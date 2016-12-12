package com.aleksandrbogomolov.repository.meal;

import com.aleksandrbogomolov.domain.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealRepository {

    Meal save(Meal meal);

    int deleteByIdAndUserId(String id, String userId);

    Meal findOneByIdAndUserId(String id, String userId);

    List<Meal> findAllByUserId(String userId);

    List<Meal> findFiltered(LocalDate startD, LocalDate endD, LocalTime startT, LocalTime endT, String userId);
}
