package se331.olympicsbackend.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se331.olympicsbackend.rest.entity.Comment;
import se331.olympicsbackend.rest.entity.Country;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAll();

    List<Comment> findByUser_IdAndCountry_Id(Integer userId,Integer countryId);
}
