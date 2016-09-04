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
                //allowing localhost to access services (WAMP)
                registry.addMapping("/prosody/changeprosody").allowedOrigins("http://localhost");
                registry.addMapping("/prosody/changetiming").allowedOrigins("http://localhost");
                registry.addMapping("/tokenize/sentenceTree").allowedOrigins("http://localhost");
                registry.addMapping("/tokenize/directTokenize").allowedOrigins("http://localhost");
                
                //allowing localhost:8088 to access serices
                registry.addMapping("/prosody/changeprosody").allowedOrigins("http://localhost:8088");
                registry.addMapping("/prosody/changetiming").allowedOrigins("http://localhost:8088");
                registry.addMapping("/tokenize/sentenceTree").allowedOrigins("http://localhost:8088");
                registry.addMapping("/tokenize/directTokenize").allowedOrigins("http://localhost:8088");
            }
        };
    }   
}
