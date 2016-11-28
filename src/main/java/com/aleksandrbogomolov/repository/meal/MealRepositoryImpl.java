package com.aleksandrbogomolov.repository.meal;

import com.aleksandrbogomolov.domain.Meal;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MealRepositoryImpl implements MealRepository {

    private final MealMongoRepository mongoRepository;

    private final MongoTemplate template;

    @Autowired
    public MealRepositoryImpl(MealMongoRepository mongoRepository, MongoTemplate template) {
        this.mongoRepository = mongoRepository;
        this.template = template;
    }

    @Override
    public Meal save(Meal meal) { return mongoRepository.save(meal); }

    @Override
    public void deleteByIdAndUserId(String id, String userId) { mongoRepository.deleteByIdAndUserId(id, userId); }

    @Override
    public Meal findOneByIdAndUserId(String id, String userId) { return mongoRepository.findOneByIdAndUserId(id, userId); }

    @Override
    public List<Meal> findAllByUserId(String userId) { return mongoRepository.findAllByUserId(userId); }

    @Override
    public List<Meal> findFiltered(LocalDate startD, LocalDate endD, LocalTime startT, LocalTime endT, String userId) {
        List<Meal> meals = new ArrayList<>();
        Meal meal;
        DBCursor cursor = template.getCollection("meals").find(new BasicDBObject("user.id", userId));
        while (cursor.hasNext()) {
            meal = template.getConverter().read(Meal.class, cursor.next());
            if (meal.getDateTime().toLocalDate().compareTo(startD) >= 0 &&
                meal.getDateTime().toLocalDate().compareTo(endD) < 1 &&
                meal.getDateTime().toLocalTime().compareTo(startT) >= 0 &&
                meal.getDateTime().toLocalTime().compareTo(endT) < 1) {
                meals.add(meal);
            }
        }
        return meals;
    }
}
