package com.aleksandrbogomolov.util;

import com.aleksandrbogomolov.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

    private final UserService service;

    @Autowired
    public SecurityUtil(UserService service) {
        this.service = service;
    }

    public String getUserId() {
        return service.findOneByName(getPrincipal()).getId();
    }

    private String getPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) return ((UserDetails) principal).getUsername();
        else return null;
    }
}
