package com.rakuten.challenge.exception;

public class BadRequestException extends BusinessException {

    public BadRequestException() {
    }

    public BadRequestException(String messageKey, String message, int httpStatus) {
        super(messageKey, message, httpStatus);
    }
}
