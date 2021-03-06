package com.aleksandrbogomolov.repository.meal;

import com.aleksandrbogomolov.domain.Meal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

@SuppressWarnings("unchecked")
public interface MealMongoRepository extends MongoRepository<Meal, String> {

    Meal save(Meal meal);

    int deleteByIdAndUserId(String id, String userId);

    Meal findOneByIdAndUserId(String id, String userId);

    List<Meal> findAllByUserId(String userId);
}
