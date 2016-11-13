package com.aleksandrbogomolov.web;

import com.aleksandrbogomolov.domain.Meal;
import com.aleksandrbogomolov.service.meal.MealService;
import com.aleksandrbogomolov.to.MealTO;
import com.aleksandrbogomolov.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meals")
public class MealRestController {

    private final MealService service;

    private final SecurityUtil util;

    @Autowired
    public MealRestController(MealService service, SecurityUtil util) {
        this.service = service;
        this.util = util;
    }

    @PostMapping
    public void save(@RequestBody Meal meal) {
        service.save(meal, util.getUserId());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id, util.getUserId());
    }

    @GetMapping("/{id}")
    public Meal getOne(@PathVariable String id) {
        return service.findOne(id, util.getUserId());
    }

    @GetMapping
    public List<MealTO> getAll() {
        return service.findAll(util.getUserId());
    }
}
