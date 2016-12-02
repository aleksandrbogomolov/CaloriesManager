package com.aleksandrbogomolov.web;

import com.aleksandrbogomolov.configuration.SecurityConfiguration;
import com.aleksandrbogomolov.domain.User;
import com.aleksandrbogomolov.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserService service;

    private final SecurityConfiguration security;

    @Autowired
    public UserRestController(UserService service, SecurityConfiguration security) {
        this.service = service;
        this.security = security;
    }

    @GetMapping("/login")
    public Map<String, Object> getPrincipal(Principal user) {
        log.info("Get principal for user: {}", user);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", user.getName());
        map.put("roles", AuthorityUtils.authorityListToSet(((Authentication) user).getAuthorities()));
        return map;
    }

    @PostMapping(value = {"", "/register"})
    public User saveUser(@RequestBody User user) {
        log.info("Save user {}", user);
        return service.save(user);
    }

    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        log.info("Delete user with name: {}", name);
        service.delete(name);
    }

    @GetMapping("/{id}")
    public User getOneById(@PathVariable String id) {
        log.info("Get user with id: {}", id);
        return service.findOneById(id);
    }

    @GetMapping("/info/{name}")
    public User getOneByName(@PathVariable String name) {
        log.info("Get user with name: {}", name);
        return service.findOneByName(name);
    }

    @GetMapping
    public List<User> getAll() {
        log.info("Get all users");
        return service.findAll();
    }
}
