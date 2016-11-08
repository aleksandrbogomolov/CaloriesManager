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
    public MealTO save(@RequestBody Meal meal, String userId) {
        return service.save(meal, MealRestController.userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public MealTO getOne(@PathVariable String id) {
        return service.findOne(id);
    }

    @GetMapping
    public List<MealTO> getAll() {
        return service.findAll();
    }
}
