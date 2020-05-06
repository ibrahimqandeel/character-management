package com.rakuten.challenge.exception;

public class InternalServerException extends BusinessException {

    public InternalServerException() {
    }

    public InternalServerException(String messageKey, String message, int httpStatus) {
        super(messageKey, message, httpStatus);
    }
}
