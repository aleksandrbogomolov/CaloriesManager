package com.aleksandrbogomolov.service.meal;

import com.aleksandrbogomolov.domain.Meal;
import com.aleksandrbogomolov.to.MealTO;

import java.util.List;

public interface MealService {

    void save(Meal meal, String userId);

    void delete(String id, String userId);

    Meal findOne(String id, String userId);

    List<MealTO> findAll(String userId);
}
