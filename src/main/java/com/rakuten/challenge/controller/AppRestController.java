package com.rakuten.challenge.controller;

import com.rakuten.challenge.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public abstract class AppRestController {
    @Autowired
    MessageSource messageSource;

    public <T extends BusinessException> T generateExceptionDetails(T ex, String messageKey, int httpStatus) {
        String message = messageSource.getMessage(messageKey, null, null);
        ex.setMessageKey(messageKey)
                .setMessage(message)
                .setHttpStatus(httpStatus);
        return ex;
    }

    public <T extends BusinessException> T generateExceptionDetailsWithParam(T ex, String messageKey, int httpStatus, Object[] param) {
        String message = messageSource.getMessage(messageKey, param, null);
        ex.setMessageKey(messageKey)
                .setMessage(message)
                .setHttpStatus(httpStatus);
        return ex;
    }
}
