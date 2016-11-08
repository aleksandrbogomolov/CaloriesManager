package com.aleksandrbogomolov.service;

import com.aleksandrbogomolov.domain.User;

import java.util.List;

public interface UserService {

    User save(User user);

    void delete(String email);

    User findOne(String email);

    List<User> findAll();
}
