package com.aleksandrbogomolov.repository.meal;

import com.aleksandrbogomolov.domain.Meal;
import com.aleksandrbogomolov.domain.User;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public List<Meal> findFiltered(LocalDate startD, LocalDate endD, LocalTime startT, LocalTime endT, User user) {
        List<Meal> meals = new ArrayList<>();
        DBCursor cursor = template.getCollection("meals").find();
        while (cursor.hasNext()) {
            Meal meal = template.getConverter().read(Meal.class, cursor.next());
            if (meal.getUser().equals(user)
                && meal.getDateTime().toLocalDate().isAfter(startD)
                && meal.getDateTime().toLocalDate().isBefore(endD)
                && meal.getDateTime().toLocalTime().toSecondOfDay() >= startT.toSecondOfDay()
                && meal.getDateTime().toLocalTime().toSecondOfDay() <= endT.toSecondOfDay()) {
                meals.add(meal);
            }
        }
        return meals;
//        return Stream.of(template.getCollection("meals").find())
//                     .map(d -> template.getConverter().read(Meal.class, d.next()))
//                     .filter(meal -> meal.getUser().equals(user)
//                                     && meal.getDateTime().toLocalDate().isAfter(startD)
//                                     && meal.getDateTime().toLocalDate().isBefore(endD)
//                                     && meal.getDateTime().toLocalTime().toSecondOfDay() >= startT.toSecondOfDay()
//                                     && meal.getDateTime().toLocalTime().toSecondOfDay() <= endT.toSecondOfDay())
//                     .collect(Collectors.toList());
    }
}
