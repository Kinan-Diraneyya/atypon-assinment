package com.kinan.atypon.assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for defining application-wide beans.
 * <p>
 * This class is used to configure and instantiate beans that are used across the application.
 * </p>
 */
@Configuration
public class AppConfig {

    /**
     * Creates and configures a {@link RestTemplate} bean.
     * <p>
     * The {@link RestTemplate} is a synchronous client to perform HTTP requests.
     * It is used to interact with external APIs and web services. This method
     * configures and returns a new instance of {@link RestTemplate}.
     * </p>
     *
     * @return a configured {@link RestTemplate} instance
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}