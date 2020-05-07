package com.rakuten.challenge.exception;

import com.rakuten.challenge.dto.ErrorMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@Slf4j
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    private String errorMessageKey = "error.general.message";
    private String errorMessage = "";

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException businessEx, WebRequest request) {
        ErrorMessageDTO errorMessageDTO = buildResponse(businessEx, request);
        return new ResponseEntity<>(errorMessageDTO, new HttpHeaders(), HttpStatus.valueOf(businessEx.getErrorMessageCode().getCode()));
    }

    private ErrorMessageDTO buildResponse(BusinessException businessEx, WebRequest request) {
        String customMessage = messageSource.getMessage(businessEx.getErrorMessageCode().getMessageKey(), businessEx.getParams(), Locale.ENGLISH);
        return ErrorMessageDTO.builder()
                .setStatus(businessEx.getErrorMessageCode().getCode())
                .setMessageKey(businessEx.getErrorMessageCode().getMessageKey())
                .setMessage(customMessage)
                .setServiceUri(request.getDescription(false))
                .setException(businessEx.getClass().getSimpleName());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        errorMessage = messageSource.getMessage("error.general.bad.request", null, Locale.ENGLISH);
        ErrorMessageDTO errorMessageDTO = ErrorMessageDTO.builder()
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setMessageKey("error.general.bad.request")
                .setMessage(errorMessage)
                .setServiceUri(request.getDescription(false))
                .setException(ex.getClass().getSimpleName());
        log.error("Error: {} Exception: {}. Reference Number: {}. Response code: {} ",
                errorMessage,
                ex.getClass().getSimpleName(),
                errorMessageDTO.getErrorReference(),
                HttpStatus.BAD_REQUEST.value());
        return handleExceptionInternal(ex, errorMessageDTO, headers, status, request);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleAny(Exception ex, WebRequest request) {
        errorMessage = messageSource.getMessage(errorMessageKey, null, Locale.ENGLISH);
        ErrorMessageDTO errorMessageDTO = ErrorMessageDTO.builder()
                .setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMessageKey(errorMessageKey)
                .setMessage(errorMessage)
                .setServiceUri(request.getDescription(false))
                .setException(ex.getClass().getSimpleName());
        log.error("Error: {} Exception: {}. Reference Number: {}. Response code: {} ",
                errorMessage,
                ex.getClass().getSimpleName(),
                errorMessageDTO.getErrorReference(),
                HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorMessageDTO, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    private String getGeneralErrorMessage(String messageKey) {
//        return messageSource.getMessage(messageKey, null, Locale.ENGLISH);
//    }

    private String getExceptionName(Exception ex) {
        if (ex.getLocalizedMessage() != null) {
            return ex.getLocalizedMessage();
        } else if (ex.getMessage() != null) {
            return ex.getMessage();
        } else if (ex.getCause() != null && ex.getCause().getLocalizedMessage() != null) {
            return ex.getCause().getLocalizedMessage();
        } else if (ex.getCause() != null && ex.getCause().getMessage() != null) {
            return ex.getCause().getMessage();
        } else {
            return "Unknown Error!";
        }
    }
}
