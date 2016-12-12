package com.aleksandrbogomolov.exception;

import static java.util.Optional.ofNullable;

public class CheckExceptionUtil {

    public static <T> T checkGetNotFound(T entity, String s) {
        return ofNullable(entity).orElseThrow(() -> new NotFoundException("Not found entity with value: " + s));
    }

    public static void checkDeleteNotFound(int found, String s) {
        if (found < 1) throw new NotFoundException("Not found entity with value: " + s);
    }
}
