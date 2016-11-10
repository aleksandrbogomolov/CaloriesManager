package com.aleksandrbogomolov.web;

import com.aleksandrbogomolov.domain.User;
import com.aleksandrbogomolov.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserService service;

    @Autowired
    public UserRestController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return service.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public User getOneById(@PathVariable int id) {
        return service.findOneById(String.valueOf(id));
    }

    @GetMapping("/search/{name}")
    public User getOneByName(@PathVariable String name) {
        return service.findOneByName(name);
    }

    @GetMapping
    public List<User> getAll() {
        return service.findAll();
    }
}
