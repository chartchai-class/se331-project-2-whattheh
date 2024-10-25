package se331.olympicsbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class OlympicsBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(OlympicsBackendApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowing PUT
                        .allowedHeaders("*") // Allowing all headers, or specify if needed
                        .exposedHeaders("x-total-count"); // to appear pagination
            }
        };
    }
}
