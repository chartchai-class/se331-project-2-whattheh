package se331.olympicsbackend.rest.security.user;


import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserDao {
    Optional<User> findById(Integer userId);
    User findByUsername(String username);
    Integer getUserSize();
    User save(User user);
    Page<User> getUsers(Integer pageSize, Integer page);

}