package com.aleksandrbogomolov.web;

import com.aleksandrbogomolov.configuration.SecurityConfiguration;
import com.aleksandrbogomolov.domain.Meal;
import com.aleksandrbogomolov.service.meal.MealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static java.util.Optional.ofNullable;

@Slf4j
@RestController
@RequestMapping("/meals")
public class MealRestController {

    private final MealService service;

    private final SecurityConfiguration security;

    @Autowired
    public MealRestController(MealService service, SecurityConfiguration security) {
        this.service = service;
        this.security = security;
    }

    @PostMapping
    public void save(@RequestBody Meal meal) {
        String userId = security.getUserId();
        service.save(meal, userId);
        log.info("Saved meal: {} for user with id: {}", meal, userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        String userId = security.getUserId();
        service.delete(id, userId);
        log.info("Deleted meal with id: {} for user with id: {}", id, userId);
    }

    @GetMapping("/{id}")
    public Meal getOne(@PathVariable String id) {
        String userId = security.getUserId();
        log.info("Get meal with id: {} for user with id: {}", id, userId);
        return service.findOne(id, userId);
    }

    @GetMapping
    public List<Meal> getAll() {
        String userId = security.getUserId();
        log.info("Get all meals for user with id: {}", userId);
        return service.findAll(userId);
    }

    @GetMapping("/filter")
    public List<Meal> getFiltered(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime) {
        String userId = security.getUserId();
        LocalDate startD = ofNullable(startDate).orElse(LocalDate.MIN);
        LocalDate endD = ofNullable(endDate).orElse(LocalDate.MAX);
        LocalTime startT = ofNullable(startTime).orElse(LocalTime.MIN);
        LocalTime endT = ofNullable(endTime).orElse(LocalTime.MAX);
        log.info("Get filtered meals from Date: {} Time: {} to Date: {} Time: {} for user with id: {}", startD, startT, endD, endT, userId);
        return service.findFiltered(startD, endD, startT, endT, userId);
    }
}
