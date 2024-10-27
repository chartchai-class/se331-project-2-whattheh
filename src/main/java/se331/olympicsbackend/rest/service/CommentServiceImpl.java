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
    public List<Comment> getComments(Integer userId,Integer countryId) {
        return commentRepository.findByUser_IdAndCountry_Id(userId,countryId);
    }
}
