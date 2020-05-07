package com.rakuten.challenge.exception;

import org.springframework.http.HttpStatus;

public enum ErrorMessageCode {
    FEIGN_CLIENT_TIMEOUT(HttpStatus.GATEWAY_TIMEOUT.value(), "error.feign.client.exception"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "error.general.message"),
    BAD_REQUEST_ERROR(HttpStatus.BAD_REQUEST.value(), "error.invalid.request"),
    RESOURCE_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND.value(), "error.general.resource.notFound"),
    UNABLE_TO_BUILD_RESPONSE(HttpStatus.UNPROCESSABLE_ENTITY.value(), "error.invalid.response"),
    CHARACTER_NAME_DUPLICATION(HttpStatus.CONFLICT.value(), "error.character.name.exist"),
    CHARACTER_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND.value(), "error.character.name.notFound");

    private int code;
    private String messageKey;

    ErrorMessageCode(int code, String messageKey) {
        this.code = code;
        this.messageKey = messageKey;
    }

    public int getCode() {
        return code;
    }

    public String getMessageKey() {
        return messageKey;
    }
}
