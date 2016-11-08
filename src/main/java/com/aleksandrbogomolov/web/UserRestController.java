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

    @DeleteMapping("/{email:.+}")
    public void delete(@PathVariable String email) {
        service.delete(email);
    }

    @GetMapping("/{email:.+}")
    public User getOne(@PathVariable String email) {
        return service.findOne(email);
    }

    @GetMapping
    public List<User> getAll() {
        return service.findAll();
    }
}
