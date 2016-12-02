package com.aleksandrbogomolov.service.user;

import com.aleksandrbogomolov.domain.User;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface UserService {

    @PreAuthorize("isAnonymous()")
    User save(User user);

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    void delete(String id);

    @PreAuthorize("hasRole('ADMIN')")
    User findOneById(String id);

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    User findOneByName(String name);

    @PreAuthorize("hasRole('ADMIN')")
    List<User> findAll();
}
