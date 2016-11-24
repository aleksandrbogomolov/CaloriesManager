package com.aleksandrbogomolov.service.meal;

import com.aleksandrbogomolov.domain.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealService {

    void save(Meal meal, String userId);

    void delete(String id, String userId);

    Meal findOne(String id, String userId);

    List<Meal> findAll(String userId);

    List<Meal> findFiltered(LocalDate startD, LocalDate endD, LocalTime startT, LocalTime endT, String userId);
}
