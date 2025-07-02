package com.example.errorhandling.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    
    private final ExampleService exampleService = new ExampleService();
    
    @GetMapping("/")
    public String home(Model model) {
        logger.info("Menerima request di Controller: Home page");
        String hasil = exampleService.doBusinessLogic();
        model.addAttribute("hasilService", hasil);
        logger.debug("DEBUG: Home page accessed for debugging");
        return "index";
    }
    
    @GetMapping("/examples")
    public String examples() {
        logger.info("Error examples page accessed");
        logger.warn("WARNING: Error examples page accessed");
        return "examples";
    }
    
    @GetMapping("/logs")
    public String logs() {
        logger.info("Logs page accessed");
        logger.error("ERROR: Logs page accessed for error demonstration");
        return "logs";
    }
}

class ExampleService {
    private static final Logger logger = LoggerFactory.getLogger(ExampleService.class);
    public String doBusinessLogic() {
        logger.info("Eksekusi logika bisnis di service");
        return "Hasil logika bisnis";
    }
}
