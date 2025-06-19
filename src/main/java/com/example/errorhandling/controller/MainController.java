package com.example.errorhandling.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    
    @GetMapping("/")
    public String home() {
        logger.info("Home page accessed");
        return "index";
    }
    
    @GetMapping("/examples")
    public String examples() {
        logger.info("Error examples page accessed");
        return "examples";
    }
    
    @GetMapping("/logs")
    public String logs() {
        logger.info("Logs page accessed");
        return "logs";
    }
}
