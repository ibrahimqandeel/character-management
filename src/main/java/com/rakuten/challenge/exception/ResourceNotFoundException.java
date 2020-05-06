package com.rakuten.challenge.exception;

public class ResourceNotFoundException extends BusinessException {

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String messageKey, String message, int httpStatus) {
        super(messageKey, message, httpStatus);
    }
}
