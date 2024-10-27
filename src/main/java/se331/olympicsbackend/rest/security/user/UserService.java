package se331.olympicsbackend.rest.security.user;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Integer getUserSize();
    User save(User user);

    @Transactional
    User findByUsername(String username);


    Page<User> getUsers(Integer pageSize,Integer page);

}