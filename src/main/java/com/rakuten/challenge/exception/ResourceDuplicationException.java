package com.rakuten.challenge.exception;

public class ResourceDuplicationException extends BusinessException {

    public ResourceDuplicationException() {
    }

    public ResourceDuplicationException(String messageKey, String message, int httpStatus) {
        super(messageKey, message, httpStatus);
    }

    public ResourceDuplicationException(String messageKey) {
        super(messageKey);
    }
}
