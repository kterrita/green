package com.greendata.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FieldIsNotAllowedForUpdateOperation extends RuntimeException {

    public FieldIsNotAllowedForUpdateOperation(String message) {
        super(message);
    }
}
