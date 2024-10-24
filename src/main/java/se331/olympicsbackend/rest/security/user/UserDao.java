package se331.olympicsbackend.rest.security.user;

public interface UserDao {
    User findByUsername(String username);

    User save(User user);
}