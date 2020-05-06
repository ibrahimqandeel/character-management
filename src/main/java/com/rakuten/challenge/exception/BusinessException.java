package com.rakuten.challenge.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends Exception {

    private String messageKey = "error.general.message";
    private String message = "Something went wrong! system unable to process your request.";
    private int httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
    private Throwable exception;

    public BusinessException() {
    }

    public BusinessException(String messageKey, String message, int httpStatus) {
        this.messageKey = messageKey;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public BusinessException(String messageKey, String message, int httpStatus, Throwable exception) {
        this.messageKey = messageKey;
        this.message = message;
        this.httpStatus = httpStatus;
        this.exception = exception;
    }

    public BusinessException(String messageKey, String message) {
        this.messageKey = messageKey;
        this.message = message;
    }

    public BusinessException(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getMessage() {
        return message;
    }

    public BusinessException setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public BusinessException setMessageKey(String messageKey) {
        this.messageKey = messageKey;
        return this;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public BusinessException setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public Throwable getException() {
        return exception;
    }

    public BusinessException setException(Throwable exception) {
        this.exception = exception;
        return this;
    }
}
