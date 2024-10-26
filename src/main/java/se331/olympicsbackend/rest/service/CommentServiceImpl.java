package se331.olympicsbackend.rest.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se331.olympicsbackend.rest.entity.Comment;
import se331.olympicsbackend.rest.repository.CommentRepository;
import se331.olympicsbackend.rest.security.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    final CommentRepository commentRepository;
    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional
    @Override
    public List<Comment> getComments(Integer userId) {
           return commentRepository.findByUserId(userId);
    }


    @Override
    public List<Comment> findByUsernameAndCountryId(String username,Integer countryId) {
        return commentRepository.findByUser_UsernameAndCountry_Id(username,countryId);
    }
}
