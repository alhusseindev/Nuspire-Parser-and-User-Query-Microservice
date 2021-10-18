package com.parsermicroservice.parser.CORSConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry myRegistry){
        myRegistry.addMapping("/")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowedOrigins("http://localhost:3000", "http://127.0.0.1:3000") //fontend's url
                .allowCredentials(true);
    }
}
