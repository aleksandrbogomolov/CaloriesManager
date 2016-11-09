package com.aleksandrbogomolov.service.meal;

import com.aleksandrbogomolov.domain.Meal;
import com.aleksandrbogomolov.repository.meal.MealRepository;
import com.aleksandrbogomolov.repository.user.UserRepository;
import com.aleksandrbogomolov.to.MealTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.aleksandrbogomolov.util.EntityConverter.convertToMealTO;
import static com.aleksandrbogomolov.util.EntityConverter.getWithExceed;

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
        meal.setUser(userRepository.findOneById(userId));
        repository.save(meal);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public Meal findOne(String id) {
        return repository.findOne(id);
    }

    @Override
    public List<MealTO> findAll() {
        return getWithExceed(repository.findAllByUserId());
    }
}
