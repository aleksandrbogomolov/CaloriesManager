package com.aleksandrbogomolov.exception;

import com.mongodb.DuplicateKeyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
@RestController
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DuplicateKeyException.class)
    public ErrorInfo handleDuplicateKeyException(HttpServletRequest req, DuplicateKeyException e) {
        return logAndGetErrorInfo(req, e);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorInfo handleNotFoundException(HttpServletRequest req, NotFoundException e) {
        return logAndGetErrorInfo(req, e);
    }

    private ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e) {
        log.warn("Exception in request " + req.getRequestURI() + " : " + e.getMessage());
        return new ErrorInfo(req.getRequestURL(), e);
    }
}
