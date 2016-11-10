package com.aleksandrbogomolov.repository.user;

import com.aleksandrbogomolov.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@SuppressWarnings("unchecked")
public interface UserRepository extends MongoRepository<User, String> {

    User save(User user);

    void deleteByName(String name);

    User findOneById(String id);

    User findOneByName(String name);

    List<User> findAll();
}
