package se331.olympicsbackend.security.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    final UserDao userDao;

    @Override
    public Integer getUserSize() {
        return userDao.getUserSize();
    }

    @Override
    @Transactional
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Page<User> getUsers(Integer pageSize, Integer page) {
        return userDao.getUsers(pageSize, page);
    }

    @Override
    @Transactional
    public User updateUserRole(Integer userId, UserDTO userDTO) {
        User user=userDao.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));

        List<Role> currentRole=user.getRoles();

        Role newRole;
        if(currentRole.contains(Role.ROLE_ADMIN)){
            newRole=Role.ROLE_USER;
        }
        else{
            newRole=Role.ROLE_ADMIN;
        }

        currentRole.clear();
        currentRole.add(newRole);
        return userDao.save(user);
    }

}