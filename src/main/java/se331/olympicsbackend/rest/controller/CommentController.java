package se331.olympicsbackend.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se331.olympicsbackend.rest.entity.Comment;
import se331.olympicsbackend.rest.entity.CommentDTO;
import se331.olympicsbackend.rest.security.user.User;
import se331.olympicsbackend.rest.service.CommentService;
import se331.olympicsbackend.rest.util.LabMapper;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    final CommentService commentService;
    @PostMapping
    public ResponseEntity<?> submitComment(@RequestBody CommentDTO commentDTO) {
        User user=new User();
        user.setId(commentDTO.getUserId());

        Comment comment=Comment.builder()
                .comment(commentDTO.getComment())
                .user(user)
                .build();

        commentService.save(comment);

        return ResponseEntity.ok(comment);

    }
    @GetMapping
    public ResponseEntity<?> loadComments(@RequestParam(value = "userId") Integer userId) {
        List<Comment> comments= commentService.getComments(userId);
        return new ResponseEntity<>
                (
                        LabMapper.INSTANCE.getCommentDTO(comments),
                        HttpStatus.OK
                );
    }
}