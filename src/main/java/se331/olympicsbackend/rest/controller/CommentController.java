package se331.olympicsbackend.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se331.olympicsbackend.rest.entity.Comment;
import se331.olympicsbackend.rest.entity.CommentDTO;
import se331.olympicsbackend.rest.entity.Country;
import se331.olympicsbackend.rest.security.user.User;
import se331.olympicsbackend.rest.service.CommentService;
import se331.olympicsbackend.rest.service.CountryService;
import se331.olympicsbackend.rest.util.LabMapper;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    final CommentService commentService;
//    final CountryService countryService;
    @PostMapping
    public ResponseEntity<?> submitComment(@RequestBody CommentDTO commentDTO) {
        User user=new User();
        user.setId(commentDTO.getUserId());
        Country country=new Country();
        country.setId(commentDTO.getCountryId());
        Comment comment=Comment.builder()
                .comment(commentDTO.getComment())
                .user(user)
                .country(country)
                .build();

        commentService.save(comment);
        return ResponseEntity.ok(comment);

    }
    @GetMapping
    public ResponseEntity<?> loadComments(
            @RequestParam(value = "userId") Integer userId,
            @RequestParam(value = "countryId")Integer countryId) {
        List<Comment> comments= commentService.getComments(userId,countryId);
        return new ResponseEntity<>
                (
                        LabMapper.INSTANCE.getCommentDTO(comments),
                        HttpStatus.OK
                );
    }
}