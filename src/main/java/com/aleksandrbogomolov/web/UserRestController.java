package com.aleksandrbogomolov.web;

import com.aleksandrbogomolov.domain.User;
import com.aleksandrbogomolov.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserService service;

    @Autowired
    public UserRestController(UserService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public Principal getOneByName(Principal user) {
        return user;
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
    public User getOneById(@PathVariable String id) {
        return service.findOneById(id);
    }

    @GetMapping
    public List<User> getAll() {
        return service.findAll();
    }
}
