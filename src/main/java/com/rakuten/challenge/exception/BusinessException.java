package com.rakuten.challenge.exception;

import lombok.Getter;

@Getter
public class BusinessException extends Exception {
    private ErrorMessageCode errorMessageCode;
    private Object[] params;

    public BusinessException(ErrorMessageCode errorMessageCode) {
        this.errorMessageCode = errorMessageCode;
    }

    public BusinessException(ErrorMessageCode errorMessageCode, Object[] params) {
        this(errorMessageCode);
        this.params = params;
    }
}
