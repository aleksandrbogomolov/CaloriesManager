package com.aleksandrbogomolov.service.meal;

import com.aleksandrbogomolov.domain.Meal;
import com.aleksandrbogomolov.repository.meal.MealRepository;
import com.aleksandrbogomolov.repository.user.UserRepository;
import com.aleksandrbogomolov.to.MealTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return repository.findOneByIdAndUserId(id, userId);
    }

    @Override
    public List<MealTO> findAll(String userId) {
        return new EntityConverter().getWithExceed(repository.findAllByUserId(userId), userId);
    }

    @Override
    public List<MealTO> findFiltered(LocalDate startD, LocalDate endD, LocalTime startT, LocalTime endT, String userId) {
        return new EntityConverter().getWithExceed(repository.findFiltered(startD, endD, startT, endT, userRepository.findOneById(userId)), userId);
    }

    private class EntityConverter {

        List<MealTO> getWithExceed(List<Meal> meals, String userId) {
            Map<LocalDate, Integer> map = meals.stream().collect(Collectors.groupingBy(m -> m.getDateTime().toLocalDate(), Collectors.summingInt(Meal::getCalories)));
            return meals.stream().map(m -> convertToMealTO(m, map.get(m.getDateTime().toLocalDate()) > userRepository.findOneById(userId).getCaloriesPerDay())).collect(Collectors.toList());
        }

        private MealTO convertToMealTO(Meal meal, boolean exceed) {
            return new MealTO(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceed);
        }
    }
}
