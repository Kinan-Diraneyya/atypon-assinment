package com.kinan.atypon.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Spring Boot application.
 * <p>
 * This class contains the main method which uses Spring Boot's {@link SpringApplication#run} method to launch the application.
 * </p>
 */
@SpringBootApplication
public class Application {

    /**
     * The main method which serves as the entry point for the Spring Boot application.
     * <p>
     * This method delegates to Spring Boot's {@link SpringApplication#run} method to bootstrap the application.
     * </p>
     *
     * @param args command-line arguments passed to the application
     */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
