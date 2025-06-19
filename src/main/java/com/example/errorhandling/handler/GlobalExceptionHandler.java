package com.example.errorhandling.handler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.errorhandling.exception.CustomBadRequestException;
import com.example.errorhandling.exception.CustomDatabaseException;
import com.example.errorhandling.exception.CustomServerException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<Map<String, String>> handleBadRequestException(CustomBadRequestException ex, WebRequest request) {
        logger.error("Handling Bad Request Exception: {}", ex.getMessage());
        Map<String, String> response = new HashMap<>();
        response.put("error", "Bad Request");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomServerException.class)
    public ResponseEntity<Map<String, String>> handleServerException(CustomServerException ex, WebRequest request) {
        logger.error("Handling Server Exception: {}", ex.getMessage());
        Map<String, String> response = new HashMap<>();
        response.put("error", "Internal Server Error");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomDatabaseException.class)
    public ResponseEntity<Map<String, String>> handleDatabaseException(CustomDatabaseException ex, WebRequest request) {
        logger.error("Handling Database Exception: {}", ex.getMessage());
        Map<String, String> response = new HashMap<>();
        response.put("error", "Database Error");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGlobalException(Exception ex, WebRequest request) {
        logger.error("Unhandled exception occurred: {}", ex.getMessage());
        Map<String, String> response = new HashMap<>();
        response.put("error", "Unexpected Error");
        response.put("message", "An unexpected error occurred. Please try again later.");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
