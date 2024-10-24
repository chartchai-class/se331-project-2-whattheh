package se331.olympicsbackend.security.user;

import org.springframework.data.domain.Page;

public interface UserDao {
    User findByUsername(String username);
    Integer getUserSize();
    User save(User user);
    Page<User> getUsers(Integer pageSize, Integer page);
}