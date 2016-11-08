package com.aleksandrbogomolov.repository.meal;

import com.aleksandrbogomolov.domain.Meal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MealRepository extends MongoRepository<Meal, String> {

    Meal save(Meal meal);

    void delete(String id);

    Meal findOne(String id);

    List<Meal> findAll();
}
