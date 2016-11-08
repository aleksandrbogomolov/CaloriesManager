package com.aleksandrbogomolov.repository.user;

import com.aleksandrbogomolov.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    User save(User user);

    void deleteByEmail(String email);

    User findByEmail(String email);

    List<User> findAll();
}
