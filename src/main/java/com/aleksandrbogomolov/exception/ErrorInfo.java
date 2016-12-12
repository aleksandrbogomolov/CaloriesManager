package com.aleksandrbogomolov.exception;

public class ErrorInfo {

    public final String url, cause;
    public final String[] detail;

    ErrorInfo(CharSequence url, Throwable ex) {
        this(url.toString(), ex.getClass().getSimpleName(), ex.getLocalizedMessage());
    }

    ErrorInfo(CharSequence requestURL, String cause, String... detail) {
        this.url = requestURL.toString();
        this.cause = cause;
        this.detail = detail;
    }
}
