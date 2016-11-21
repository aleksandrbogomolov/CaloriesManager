package com.aleksandrbogomolov.web;

import com.aleksandrbogomolov.configuration.SecurityConfiguration;
import com.aleksandrbogomolov.domain.Meal;
import com.aleksandrbogomolov.service.meal.MealService;
import com.aleksandrbogomolov.to.MealTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static java.util.Optional.ofNullable;

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
        service.save(meal, security.getUserId());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id, security.getUserId());
    }

    @GetMapping("/{id}")
    public Meal getOne(@PathVariable String id) {
        return service.findOne(id, security.getUserId());
    }

    @GetMapping
    public List<MealTO> getAll() {
        return service.findAll(security.getUserId());
    }

    @GetMapping("/filter")
    public List<MealTO> getFiltered(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime) {
        return service.findFiltered(
                ofNullable(startDate).orElse(LocalDate.MIN),
                ofNullable(endDate).orElse(LocalDate.MAX),
                ofNullable(startTime).orElse(LocalTime.MIN),
                ofNullable(endTime).orElse(LocalTime.MAX),
                security.getUserId());
    }
}
