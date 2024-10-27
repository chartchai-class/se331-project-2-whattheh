package se331.olympicsbackend.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se331.olympicsbackend.rest.entity.Comment;
import se331.olympicsbackend.rest.entity.Country;
import se331.olympicsbackend.rest.repository.CountryRepository;
import se331.olympicsbackend.rest.service.AICommentTranslateService;

import java.util.Optional;


@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final AICommentTranslateService aiCommentService;
    private final CountryRepository countryRepository;

    @PostMapping("/{countryId}")
    public ResponseEntity<Comment> addComment(
            @PathVariable Long countryId, @RequestBody String originalText) {

        // Translate the comment to English
        String translatedText = aiCommentService.translateToEnglish(originalText);

        // Find the country associated with the comment
        Optional<Country> countryOpt = countryRepository.findById(countryId);
        if (countryOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Save the comment
        Comment comment = Comment.builder()
                .originalText(originalText)
                .translatedText(translatedText)
                .country(countryOpt.get())
                .build();

        Comment savedComment = aiCommentService.saveComment(comment);
        return ResponseEntity.ok(savedComment);
    }
}
