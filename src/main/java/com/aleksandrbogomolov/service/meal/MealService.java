package com.aleksandrbogomolov.service.meal;

import com.aleksandrbogomolov.domain.Meal;

import java.util.List;

public interface MealService {

    Meal save(Meal meal);

    void delete(String id);

    Meal findOne(String id);

    List<Meal> findAll();
}
