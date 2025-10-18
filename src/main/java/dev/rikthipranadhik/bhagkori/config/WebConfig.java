package dev.rikthipranadhik.bhagkori.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply this rule to all endpoints
                .allowedOrigins(
                        "http://localhost:5173", // Your local React dev server
                        "https://black-beach-0d69cae0f.3.azurestaticapps.net" // Your deployed frontend URL
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
