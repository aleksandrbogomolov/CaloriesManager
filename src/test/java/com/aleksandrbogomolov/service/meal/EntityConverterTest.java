package com.aleksandrbogomolov.service.meal;

import com.aleksandrbogomolov.AbstractTest;
import com.aleksandrbogomolov.repository.meal.MealRepository;
import com.aleksandrbogomolov.repository.user.UserRepository;
import com.aleksandrbogomolov.to.MealTO;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EntityConverterTest extends AbstractTest {

    private final MealRepository mealRepository = mock(MealRepository.class);

    private final UserRepository userRepository = mock(UserRepository.class);

    private final MealTO testMealTO1 = new MealTO(String.valueOf(startId + 3), LocalDateTime.of(2016, 11, 10, 10, 8), "Завтрак", 500, false);

    private final MealTO testMealTO2 = new MealTO(String.valueOf(startId + 4), LocalDateTime.of(2016, 11, 12, 13, 0), "Обед", 1000, false);

    @Test
    public void getWithExceed() throws Exception {
        MealService service = new MealServiceImpl(mealRepository, userRepository);
        when(mealRepository.findAllByUserId(loggedUser.getId())).thenReturn(Arrays.asList(testMeal1, testMeal2));
        when(userRepository.findOneById(loggedUser.getId())).thenReturn(loggedUser);
        assertArrayEquals(service.findAll(loggedUser.getId()).toArray(), new MealTO[]{testMealTO1, testMealTO2});
    }
}
