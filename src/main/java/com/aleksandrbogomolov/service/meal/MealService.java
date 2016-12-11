package com.aleksandrbogomolov.service.meal;

import com.aleksandrbogomolov.domain.Meal;
import com.aleksandrbogomolov.exception.NotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealService {

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    void save(Meal meal, String userId);

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    void delete(String id, String userId);

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    Meal findOne(String id, String userId) throws NotFoundException;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    List<Meal> findAll(String userId);

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    List<Meal> findFiltered(LocalDate startD, LocalDate endD, LocalTime startT, LocalTime endT, String userId);
}
