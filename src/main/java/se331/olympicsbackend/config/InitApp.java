package se331.olympicsbackend.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.olympicsbackend.security.user.Role;
import se331.olympicsbackend.security.user.User;
import se331.olympicsbackend.security.user.UserRepository;

@Component
@RequiredArgsConstructor

public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final UserRepository userRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        addUser();
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


}
