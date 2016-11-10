package com.aleksandrbogomolov.service.user;

import com.aleksandrbogomolov.domain.User;
import com.aleksandrbogomolov.repository.user.UserRepository;
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
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(String email) {
        repository.deleteByName(email);
    }

    @Override
    public User findOneById(String id) {
        return repository.findOneById(id);
    }

    @Override
    public User findOneByName(String email) {
        return repository.findOneByName(email);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
}
