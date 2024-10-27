package se331.olympicsbackend.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.olympicsbackend.rest.entity.Comment;
import se331.olympicsbackend.rest.entity.Country;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAll();
}
