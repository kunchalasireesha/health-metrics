package com.example.health_metrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example") // This starts everything
@EntityScan("com.example.model")
@EnableJpaRepositories("com.example.repository")
public class HealthMetricsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthMetricsApplication.class, args);
	}

}
