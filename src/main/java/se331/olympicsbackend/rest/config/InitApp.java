package se331.olympicsbackend.rest.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
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
import se331.olympicsbackend.rest.entity.*;
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
        addCountryFromApi();
    }

    private void addCountryFromApi() {
    }

    User user1, user2;

    private void addUser() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user1 = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .email("admin@gmail.com")
                .enabled(true)
                .build();

        user2 = User.builder()
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

           // System.out.println("API Response: " + response.getBody());

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode dataArray = rootNode.get("data");

            // Parse all medals at once
            List<MedalDTO> medalDTOs = Arrays.asList(objectMapper.treeToValue(dataArray, MedalDTO[].class));
            System.out.println("Parsed Medals: " + medalDTOs);

            // Parse countries only once
            for (JsonNode countryNode : dataArray) {
                String countryName = countryNode.get("name").asText();
                String flag = countryNode.get("flag_url").asText();

                // Create or retrieve the country object
                Country country = countryRepository.findByCountryName(countryName)
                        .orElseGet(() -> Country.builder()
                                .countryName(countryName)
                                .flag(flag)
                                .build());

                countryRepository.save(country);



                // Parse and save sports specific to the country
                JsonNode sportsArray = countryNode.get("sports");
                List<SportDTO> sports = Arrays.asList(
                        objectMapper.treeToValue(sportsArray, SportDTO[].class)
                );

                System.out.println("Parsed sports: " + sports);

                sports.forEach(sportdto -> {
                    Sport sport = Sport.builder()
                            .sportName(sportdto.getSportName())
                            .gold(sportdto.getGold())
                            .silver(sportdto.getSilver())
                            .bronze(sportdto.getBronze())
                            .total(sportdto.getTotals())
                            .country(country)
                            .build();
                    sportRepository.save(sport);
                    // Parse and save medals specific to the country
                    List<Medal> medals = medalDTOs.stream()
                            .map(dto -> Medal.builder()
                                    .gold(dto.getGold())
                                    .silver(dto.getSilver())
                                    .bronze(dto.getBronze())
                                    .totalMedals(dto.getTotalMedals())
                                    .ranking(dto.getRanking())
                                    .totalRank(dto.getTotalRank())
                                    .country(country)
                                    .sport(sport)
                                    .build())
                            .toList();
                    medalRepository.saveAll(medals);
                });

            }

            System.out.println("Sports data fetched from API and saved successfully.");

        } catch (JsonProcessingException e) {
            System.err.println("Error processing JSON: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error fetching sports data from API: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
