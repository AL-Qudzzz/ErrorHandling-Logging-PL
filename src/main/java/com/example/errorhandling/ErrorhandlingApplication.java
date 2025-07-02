package com.example.errorhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class ErrorhandlingApplication {
	private static final Logger logger = LoggerFactory.getLogger(ErrorhandlingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ErrorhandlingApplication.class, args);
	}

	// Contoh aktivitas terjadwal
	@Scheduled(fixedRate = 60000) // Setiap 1 menit
	public void scheduledTaskExample() {
		logger.info("Aktivitas terjadwal dieksekusi setiap 1 menit");
	}
}
