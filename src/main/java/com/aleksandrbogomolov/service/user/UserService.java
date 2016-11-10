package com.aleksandrbogomolov.service.user;

import com.aleksandrbogomolov.domain.User;

import java.util.List;

public interface UserService {

    User save(User user);

    void delete(String id);

    User findOneById(String id);

    User findOneByName(String email);

    List<User> findAll();
}
