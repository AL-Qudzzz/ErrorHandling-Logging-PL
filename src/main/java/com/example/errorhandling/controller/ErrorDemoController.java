package com.example.errorhandling.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.errorhandling.exception.CustomBadRequestException;
import com.example.errorhandling.exception.CustomDatabaseException;
import com.example.errorhandling.exception.CustomServerException;

@RestController
@RequestMapping("/api/error")
public class ErrorDemoController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorDemoController.class);

    @GetMapping("/not-found")
    public ResponseEntity<Map<String, String>> triggerNotFoundError() {
        logger.error("404 Not Found error triggered intentionally");
        Map<String, String> response = new HashMap<>();
        response.put("error", "Resource Not Found");
        response.put("message", "The requested resource could not be found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/bad-request")
    public ResponseEntity<Map<String, String>> triggerBadRequestError() {
        logger.error("400 Bad Request error triggered intentionally");
        throw new CustomBadRequestException("Invalid request parameters provided.");
    }

    @GetMapping("/unauthorized")
    public ResponseEntity<Map<String, String>> triggerUnauthorizedError() {
        logger.error("401 Unauthorized error triggered intentionally");
        Map<String, String> response = new HashMap<>();
        response.put("error", "Unauthorized");
        response.put("message", "Authentication credentials are missing or invalid.");
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/forbidden")
    public ResponseEntity<Map<String, String>> triggerForbiddenError() {
        logger.error("403 Forbidden error triggered intentionally");
        Map<String, String> response = new HashMap<>();
        response.put("error", "Forbidden");
        response.put("message", "You do not have permission to access this resource.");
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @GetMapping("/server-error")
    public ResponseEntity<Map<String, String>> triggerServerError() {
        logger.error("500 Internal Server Error triggered intentionally");
        throw new CustomServerException("An unexpected server error occurred.");
    }
 
    @GetMapping("/service-unavailable")
    public ResponseEntity<Map<String, String>> triggerServiceUnavailableError() {
        logger.error("503 Service Unavailable error triggered intentionally");
        Map<String, String> response = new HashMap<>();
        response.put("error", "Service Unavailable");
        response.put("message", "The server is temporarily unable to handle the request.");
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/validation")
    public ResponseEntity<Map<String, String>> triggerValidationError() {
        logger.error("Validation error triggered intentionally");
        Map<String, String> response = new HashMap<>();
        response.put("error", "Validation Failed");
        response.put("message", "Input validation failed. Please check your data.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/database")
    public ResponseEntity<Map<String, String>> triggerDatabaseError() {
        logger.error("Database error triggered intentionally");
        logger.info("Simulasi interaksi dengan database: SELECT * FROM users");
        throw new CustomDatabaseException("Database operation failed due to connection issues.");
    }

    @GetMapping("/conflict")
    public ResponseEntity<Map<String, String>> triggerConflictError() {
        logger.error("409 Conflict error triggered intentionally");
        Map<String, String> response = new HashMap<>();
        response.put("error", "Conflict");
        response.put("message", "There is a conflict with the current state of the resource.");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @GetMapping("/method-not-allowed")
    public ResponseEntity<Map<String, String>> triggerMethodNotAllowedError() {
        logger.error("405 Method Not Allowed error triggered intentionally");
        Map<String, String> response = new HashMap<>();
        response.put("error", "Method Not Allowed");
        response.put("message", "The HTTP method used is not supported for this resource.");
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
