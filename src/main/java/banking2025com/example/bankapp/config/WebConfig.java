// backend/src/main/java/banking2025com/example/bankapp/config/WebConfig.java
package banking2025com.example.bankapp.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import banking2025com.example.bankapp.dto.TransactionDTO;
import banking2025com.example.bankapp.entity.Transaction;

@Configuration
public class WebConfig {

    @Bean
    @Primary
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        // … your existing mappings …
        return mapper;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:8081")
                        .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}

