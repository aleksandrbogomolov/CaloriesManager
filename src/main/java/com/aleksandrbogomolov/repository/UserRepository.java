package com.aleksandrbogomolov.repository;

import com.aleksandrbogomolov.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    User save(User user);

    void delete(String email);

    User findByEmail(String email);

    List<User> findAll();
}
