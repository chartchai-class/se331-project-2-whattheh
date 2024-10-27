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
import java.util.Optional;


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

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode dataArray = rootNode.get("data");

            // Iterate over each country node from API response
            for (JsonNode countryNode : dataArray) {
                String countryName = countryNode.get("name").asText();
                String flag = countryNode.get("flag_url").asText();

                // Fetch or create a new Country entity
                Country country = countryRepository.findByCountryName(countryName)
                        .orElseGet(() -> Country.builder()
                                .countryName(countryName)
                                .flag(flag)
                                .build());

                countryRepository.save(country);
                System.out.println("Parsed country: " + country);

                // Save the Medal information for the country
                if (country.getMedal() == null) {  // Ensure the medal isn't duplicated
                    Medal medal = Medal.builder()
                            .gold_medals(countryNode.get("gold_medals").asInt())
                            .silver_medals(countryNode.get("silver_medals").asInt())
                            .bronze_medals(countryNode.get("bronze_medals").asInt())
                            .total_medals(countryNode.get("total_medals").asInt())
                            .ranking(countryNode.get("rank").asInt())
                            .totalRank(countryNode.get("rank_total_medals").asInt())
                            .country(country)

                            .build();

                    medalRepository.save(medal);
                    country.setMedal(medal);  // Link the medal to the country
                    countryRepository.save(country);
                    System.out.println("Inserted new medal: " + medal);
                } else {
                    System.out.println("Medal already exists for country: " + country.getCountryName());
                }

                // Iterate over each sport in the country node
                JsonNode sportsArray = countryNode.get("sports");
                for (JsonNode sportNode : sportsArray) {
                    String sportName = sportNode.get("name").asText();

                    // Check if the sport already exists for this country to prevent duplication
                    Optional<Sport> existingSport = sportRepository.findBySportNameAndCountryId(sportName, country.getId());

                    if (existingSport.isEmpty()) {
                        Sport sport = Sport.builder()
                                .sportName(sportName)
                                .gold(sportNode.get("gold").asInt())
                                .silver(sportNode.get("silver").asInt())
                                .bronze(sportNode.get("bronze").asInt())
                                .total(sportNode.get("total").asInt())
                                .country(country)
                                .build();

                        sportRepository.save(sport);
                        System.out.println("Inserted new sport: " + sport);
                    } else {
                        System.out.println("Sport already exists: " + existingSport.get().getSportName());
                    }
                }
            }

            System.out.println("Data from API fetched and saved successfully.");

        } catch (JsonProcessingException e) {
            System.err.println("Error processing JSON: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error fetching data from API: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
