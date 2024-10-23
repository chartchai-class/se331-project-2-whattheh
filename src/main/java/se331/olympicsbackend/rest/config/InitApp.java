package se331.olympicsbackend.rest.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import se331.olympicsbackend.rest.entity.Country;
import se331.olympicsbackend.rest.entity.Medal;
import se331.olympicsbackend.rest.entity.MedalDTO;
import se331.olympicsbackend.rest.repository.CountryRepository;
import se331.olympicsbackend.rest.repository.MedalRepository;
import se331.olympicsbackend.rest.repository.SportRepository;
import se331.olympicsbackend.rest.security.user.Role;
import se331.olympicsbackend.rest.security.user.User;
import se331.olympicsbackend.rest.security.user.UserRepository;

import java.util.Arrays;
import java.util.List;


@Component
@RequiredArgsConstructor


public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
     final MedalRepository medalRepository;
     final CountryRepository countryRepository;
     final SportRepository sportRepository;
     final UserRepository userRepository;
    final RestTemplate restTemplate;


    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {

        addUser();
        addMedalsFromApi();
    }
    User user1,user2;
    private void addUser(){
        PasswordEncoder encoder=new BCryptPasswordEncoder();
        user1=User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .email("admin@gmail.com")
                .enabled(true)
                .build();

        user2=User.builder()
                .username("user")
                .password(encoder.encode("user"))
                .email("user@gmail.com")
                .enabled(true)
                .build();

        user1.getRoles().add(Role.ROLE_ADMIN);
        user2.getRoles().add(Role.ROLE_USER);

        userRepository.save(user1);
        userRepository.save(user2);
    }
    private void addMedalsFromApi() {

            String apiUrl = "https://cfaef2cc-2a38-4135-b81b-a179cf52e24d.mock.pstmn.io/demo";

            try {
                HttpHeaders headers = new HttpHeaders();
                headers.set("Accept", "application/json");
                HttpEntity<String> entity = new HttpEntity<>(headers);

                ResponseEntity<String> response = restTemplate.exchange(
                        apiUrl, HttpMethod.GET, entity, String.class);

                System.out.println("API Response: " + response.getBody());

            } catch (Exception e) {
                System.err.println("Error fetching medals from API: " + e.getMessage());
                e.printStackTrace();
            }
        }


    }