package com.rakuten.challenge.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ErrorMessageDTO {
    private int status;
    private String message;
    private String messageKey;
    private String serviceUri;
    private String exception;
    private Long timestamp;
    private String dateTime;
    private Long errorReference = (long) (Math.floor(Math.random() * 900000L) + 100000L);
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
    private Date currentDate = new Date();

    public ErrorMessageDTO() {
    }

    public static ErrorMessageDTO builder() {
        return new ErrorMessageDTO();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public String getServiceUri() {
        return serviceUri;
    }

    public String getException() {
        return exception;
    }

    public Long getTimestamp() {
        timestamp = currentDate.getTime();
        return timestamp;
    }

    public String getDateTime() {
        dateTime = simpleDateFormat.format(currentDate);
        return dateTime;
    }

    public Long getErrorReference() {
        return errorReference;
    }

    public ErrorMessageDTO setStatus(int status) {
        this.status = status;
        return this;
    }

    public ErrorMessageDTO setMessage(String message) {
        this.message = message;
        return this;
    }

    public ErrorMessageDTO setMessageKey(String messageKey) {
        this.messageKey = messageKey;
        return this;
    }

    public ErrorMessageDTO setServiceUri(String serviceUri) {
        this.serviceUri = serviceUri;
        return this;
    }

    public ErrorMessageDTO setException(String exception) {
        this.exception = exception;
        return this;
    }
}
