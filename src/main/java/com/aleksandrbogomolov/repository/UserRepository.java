package com.aleksandrbogomolov.repository;

import com.aleksandrbogomolov.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends MongoRepository<User, String> {

    User save(User user);

    void delete(String email);

    User findOne(String email);

    List<User> findAll();
}
