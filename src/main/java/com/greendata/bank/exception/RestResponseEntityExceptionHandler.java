package com.greendata.bank.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {BankNotFoundException.class, ClientNotFoundException.class, DepositNotFoundException.class, FieldIsNotAllowedForUpdateOperation.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        HttpStatus status;
        if (ex instanceof FieldIsNotAllowedForUpdateOperation) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), status, request);
    }
}
