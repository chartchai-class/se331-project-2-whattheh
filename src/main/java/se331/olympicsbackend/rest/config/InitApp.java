package se331.olympicsbackend.rest.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.olympicsbackend.rest.entity.Country;
import se331.olympicsbackend.rest.entity.Medal;
import se331.olympicsbackend.rest.repository.CountryRepository;
import se331.olympicsbackend.rest.repository.MedalRepository;
import se331.olympicsbackend.rest.repository.SportRepository;
import se331.olympicsbackend.rest.security.user.Role;
import se331.olympicsbackend.rest.security.user.User;
import se331.olympicsbackend.rest.security.user.UserRepository;

import java.util.List;


@Component
@RequiredArgsConstructor

public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
     final MedalRepository medalRepository;
     final CountryRepository countryRepository;
     final SportRepository sportRepository;
     final UserRepository userRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {

        addUser();
        addMedals();
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
    private void addMedals() {
        List<Medal> medals = List.of(
                Medal.builder().goldMedals(5).silverMedals(3).bronzeMedals(2).totalMedals(10).build(),
                Medal.builder().goldMedals(1).silverMedals(1).bronzeMedals(1).totalMedals(3).build()
        );

        medalRepository.saveAll(medals);
        System.out.println("Medals saved successfully.");
    }

}