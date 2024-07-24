package com.kinan.atypon.assignment.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class to set up CORS (Cross-Origin Resource Sharing) for the application.
 * <p>
 * This class defines the global CORS configuration to allow requests from specific origins.
 * </p>
 */
@Configuration
public class WebConfig {

    /**
     * Configures CORS mappings for the application.
     * <p>
     * This method returns a {@link WebMvcConfigurer} bean that sets up the CORS configuration
     * to allow requests from the specified origins with the specified methods and headers.
     * </p>
     *
     * @return a {@link WebMvcConfigurer} with the CORS configuration
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000", "http://frontend:3000") // Change this to your frontend's URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}