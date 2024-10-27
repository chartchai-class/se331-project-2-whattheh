package se331.olympicsbackend.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se331.olympicsbackend.rest.entity.Comment;
import se331.olympicsbackend.rest.repository.CommentRepository;
import se331.olympicsbackend.rest.security.user.User;

import java.util.List;

@Service
public interface CommentService {
    Comment save(Comment comment);
    List<Comment> getComments(Integer userId);
    List<Comment> findByUsernameAndCountryId(String username,Integer countryId);
}
