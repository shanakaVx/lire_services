package com.ServiceController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {
            SpringApplication.run(ServiceApplication.class, args);
            System.out.println("Lire Services started!");
    }
        
        
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/prosody/changepitch").allowedOrigins("http://localhost:8383");
                registry.addMapping("/prosody/changepitch").allowedOrigins("http://localhost:8383");
                registry.addMapping("/prosody/changepitch").allowedOrigins("http://localhost:8383");
                registry.addMapping("/prosody/changepitch").allowedOrigins("http://localhost:8383");
            }
        };
    }   
}
