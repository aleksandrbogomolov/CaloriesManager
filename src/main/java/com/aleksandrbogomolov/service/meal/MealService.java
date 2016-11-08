package com.aleksandrbogomolov.service.meal;

import com.aleksandrbogomolov.domain.Meal;
import com.aleksandrbogomolov.to.MealTO;

import java.util.List;

public interface MealService {

    MealTO save(Meal meal, String userId);

    void delete(String id);

    MealTO findOne(String id);

    List<MealTO> findAll();
}
