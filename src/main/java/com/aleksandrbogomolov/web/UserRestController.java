package com.aleksandrbogomolov.web;

import com.aleksandrbogomolov.domain.User;
import com.aleksandrbogomolov.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
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
        log.info("Get principal for user: {}", user);
        return user;
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        log.info("Save user {}", user);
        return service.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        log.info("Delete user with id: {}", id);
        service.delete(id);
    }

    @GetMapping("/{id}")
    public User getOneById(@PathVariable String id) {
        log.info("Get user with id: {}", id);
        return service.findOneById(id);
    }

    @GetMapping
    public List<User> getAll() {
        log.info("Get all users");
        return service.findAll();
    }
}
