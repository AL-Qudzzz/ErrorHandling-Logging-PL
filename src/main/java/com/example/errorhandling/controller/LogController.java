package com.example.errorhandling.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);
    private static final String LOG_FILE_PATH = "logs/spring-boot-logger.log";

    @GetMapping
    public ResponseEntity<Map<String, List<String>>> getLogs() {
        logger.info("Retrieving application logs");
        List<String> logs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logs.add(line);
            }
        } catch (IOException e) {
            logger.error("Error reading log file: {}", e.getMessage());
            logs.add("Error: Unable to retrieve logs at this time.");
        }
        
        Map<String, List<String>> response = new HashMap<>();
        response.put("logs", logs);
        return ResponseEntity.ok(response);
    }
}
