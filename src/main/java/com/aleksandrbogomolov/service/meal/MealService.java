package com.aleksandrbogomolov.service.meal;

import com.aleksandrbogomolov.domain.Meal;
import com.aleksandrbogomolov.to.MealTO;

import java.util.List;

public interface MealService {

    void save(Meal meal, String userId);

    void delete(String id);

    Meal findOne(String id);

    List<MealTO> findAll();
}
