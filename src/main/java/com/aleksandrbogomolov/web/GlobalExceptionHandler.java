package com.aleksandrbogomolov.web;

import com.mongodb.DuplicateKeyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DuplicateKeyException.class)
    public ErrorInfo handleDuplicateKeyException(HttpServletRequest req, DuplicateKeyException e) {
        return logAndGetErrorInfo(req, e);
    }

    private ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e) {
        log.warn("Exception in request " + req.getRequestURI(), e.getMessage());
        return new ErrorInfo(req.getRequestURL(), e);
    }
}

class ErrorInfo {

    public final String url, cause, detail;

    ErrorInfo(CharSequence url, Throwable ex) {
        this.url = url.toString();
        this.cause = ex.getClass().getSimpleName();
        this.detail = ex.getLocalizedMessage();
    }
}
