package com.aleksandrbogomolov.web;

import com.aleksandrbogomolov.domain.Meal;
import com.aleksandrbogomolov.service.meal.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meal")
public class MealRestController {

    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    @PostMapping
    public Meal save(@RequestBody Meal meal) {
        return service.save(meal);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public Meal getOne(@PathVariable String id) {
        return service.findOne(id);
    }

    @GetMapping
    public List<Meal> getAll() {
        return service.findAll();
    }
}
