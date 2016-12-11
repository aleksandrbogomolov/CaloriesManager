package com.aleksandrbogomolov.exception;

public class ErrorInfo {

    public final String url, cause, detail;

    ErrorInfo(CharSequence url, Throwable ex) {
        this.url = url.toString();
        this.cause = ex.getClass().getSimpleName();
        this.detail = ex.getLocalizedMessage();
    }
}
