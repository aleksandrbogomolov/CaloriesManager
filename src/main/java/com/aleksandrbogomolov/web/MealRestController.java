package com.aleksandrbogomolov.web;

import com.aleksandrbogomolov.domain.Meal;
import com.aleksandrbogomolov.service.meal.MealService;
import com.aleksandrbogomolov.to.MealTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meal")
public class MealRestController {

    private static final String userId = "1";

    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    @PostMapping
    public void save(@RequestBody Meal meal) {
        service.save(meal, userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id, userId);
    }

    @GetMapping("/{id}")
    public Meal getOne(@PathVariable String id) {
        return service.findOne(id, userId);
    }

    @GetMapping
    public List<MealTO> getAll() {
        return service.findAll(userId);
    }
}
