package com.aleksandrbogomolov.exception;

import com.mongodb.DuplicateKeyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ErrorInfo handleBindingException(HttpServletRequest req, BindingResult result) {
        return logAndGetValidationErrorInfo(req, result);
    }

    private ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e) {
        log.warn("Exception in request " + req.getRequestURI() + " : " + e.getMessage());
        return new ErrorInfo(req.getRequestURL(), e);
    }

    private ErrorInfo logAndGetValidationErrorInfo(HttpServletRequest req, BindingResult result) {
        String[] detail = result.getFieldErrors()
                .stream()
                .map(fe -> fe.getField() + ' ' + fe.getDefaultMessage())
                .toArray(String[]::new);

        log.warn("Validation exception at request " + req.getRequestURL() + ": " + Arrays.toString(detail));
        return new ErrorInfo(req.getRequestURL(), "ValidationException", detail);
    }
}
