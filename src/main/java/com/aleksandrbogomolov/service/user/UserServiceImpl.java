package com.aleksandrbogomolov.service.user;

import com.aleksandrbogomolov.domain.User;
import com.aleksandrbogomolov.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.aleksandrbogomolov.exception.CheckExceptionUtil.checkDeleteNotFound;
import static com.aleksandrbogomolov.exception.CheckExceptionUtil.checkGetNotFound;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        if (user.isNew()) user.setId(null);
        return repository.save(user);
    }

    @Override
    public void delete(String name) {
        checkDeleteNotFound(repository.deleteByName(name), name);
    }

    @Override
    public User findOneByName(String name) {
        return checkGetNotFound(repository.findOneByName(name), name);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = Optional.ofNullable(repository.findOneByName(s))
                .orElseThrow(() -> new UsernameNotFoundException("User with name: " + s + " not found"));

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
    }
}
