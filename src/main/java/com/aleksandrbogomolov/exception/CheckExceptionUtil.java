package com.aleksandrbogomolov.exception;

import static java.util.Optional.ofNullable;

public class CheckExceptionUtil {

    public static <T> T checkNotFound(T entity, String s) {
        return ofNullable(entity).orElseThrow(() -> new NotFoundException("Not found entity with value: " + s));
    }
}
