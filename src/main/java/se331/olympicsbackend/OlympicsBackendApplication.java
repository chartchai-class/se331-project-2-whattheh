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
                        .exposedHeaders("x-total-count"); // to appear pagination registry.addMapping("/api/**")  // Allow all endpoints under /api/
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:3000")  // Allow requests from your frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allowed HTTP methods
                        .allowedHeaders("*")  // Allow all headers
                        .allowCredentials(true);  // Allow cookies if needed
            }
        };
    }
}
