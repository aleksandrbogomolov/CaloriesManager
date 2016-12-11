package com.aleksandrbogomolov.service.meal;

import com.aleksandrbogomolov.domain.Meal;
import com.aleksandrbogomolov.repository.meal.MealRepository;
import com.aleksandrbogomolov.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.aleksandrbogomolov.exception.CheckExceptionUtil.checkNotFound;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository repository;

    private final UserRepository userRepository;

    @Autowired
    public MealServiceImpl(MealRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(Meal meal, String userId) {
        if (meal.isNew()) meal.setId(null);
        meal.setUser(userRepository.findOneById(userId));
        repository.save(meal);
    }

    @Override
    public void delete(String id, String userId) {
        repository.deleteByIdAndUserId(id, userId);
    }

    @Override
    public Meal findOne(String id, String userId) {
        return checkNotFound(repository.findOneByIdAndUserId(id, userId), id);
    }

    @Override
    public List<Meal> findAll(String userId) {
        return checkExceed(repository.findAllByUserId(userId), userId);
    }

    @Override
    public List<Meal> findFiltered(LocalDate startD, LocalDate endD, LocalTime startT, LocalTime endT, String userId) {
        return checkExceed(repository.findFiltered(startD, endD, startT, endT, userId), userId);
    }

    private List<Meal> checkExceed(List<Meal> meals, String userId) {
        int caloriesPerDay = userRepository.findOneById(userId).getCaloriesPerDay();
        Map<LocalDate, Integer> map = meals.stream().collect(Collectors.groupingBy(m -> m.getDateTime().toLocalDate(), Collectors.summingInt(Meal::getCalories)));
        return meals.stream().parallel().map(m -> updateExceed(m, map.get(m.getDateTime().toLocalDate()) > caloriesPerDay)).collect(Collectors.toList());
    }

    private Meal updateExceed(Meal meal, boolean exceed) {
        meal.setExceed(exceed);
        return meal;
    }
}
