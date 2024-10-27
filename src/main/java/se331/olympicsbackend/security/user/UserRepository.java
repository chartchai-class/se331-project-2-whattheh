package se331.olympicsbackend.security.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<se331.olympicsbackend.security.user.User, Integer> {
  List<User> findAll();
  Optional<User> findByEmail(String email);
  Optional<User> findByUsername(String username);

}
