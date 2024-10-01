package com.e_commerce.E_commerce;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedOrigins("http://localhost:3002")  // React uygulamasının adresi (localhost:3000)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // İzin verilen HTTP metotları
                .allowedHeaders("*")
                .allowedHeaders("Authorization", "Content-Type")// İzin verilen header'lar
                .allowCredentials(true);
    }

}
