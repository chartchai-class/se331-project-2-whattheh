package se331.olympicsbackend.security.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<se331.olympicsbackend.security.user.User, Integer> {

  Optional<se331.olympicsbackend.security.user.User> findByEmail(String email);

  Optional<se331.olympicsbackend.security.user.User> findByUsername(String username);

}
