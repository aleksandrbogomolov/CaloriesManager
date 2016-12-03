package com.aleksandrbogomolov.service.user;

import com.aleksandrbogomolov.domain.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface UserService {

    @PreAuthorize("isAnonymous() or authenticated")
    User save(User user);

    @PreAuthorize("authenticated")
    void delete(String name);

    @PreAuthorize("authenticated")
    User findOneByName(String name);

    @PreAuthorize("hasRole('ADMIN')")
    List<User> findAll();
}
