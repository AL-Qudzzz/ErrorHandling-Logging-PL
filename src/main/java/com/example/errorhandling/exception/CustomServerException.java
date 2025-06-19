package com.example.errorhandling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomServerException extends RuntimeException {
    public CustomServerException(String message) {
        super(message);
    }
}
