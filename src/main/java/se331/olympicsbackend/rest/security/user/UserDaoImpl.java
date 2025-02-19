package se331.olympicsbackend.rest.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    final UserRepository userRepository;

    @Override

    public Optional<User> findById(Integer userId) {

        return userRepository.findById(userId);

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public Integer getUserSize() {
        return Math.toIntExact(userRepository.count());
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Page<User> getUsers(Integer pageSize,Integer page) {
        return userRepository.findAll(PageRequest.of(page-1, pageSize));
    }
}