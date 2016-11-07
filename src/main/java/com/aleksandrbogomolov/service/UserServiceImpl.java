package com.aleksandrbogomolov.service;

import com.aleksandrbogomolov.domain.User;
import com.aleksandrbogomolov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public void delete(String email) {
        repository.delete(email);
    }

    @Override
    public User findOne(String email) {
        return repository.findOne(email);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
}
