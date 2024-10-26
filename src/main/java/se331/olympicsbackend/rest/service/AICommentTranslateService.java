package se331.olympicsbackend.rest.service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
        import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se331.olympicsbackend.rest.entity.Comment;
import se331.olympicsbackend.rest.repository.CommentRepository;
import se331.olympicsbackend.rest.repository.CountryRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class  AICommentTranslateService {

    private final CommentRepository commentRepository;
    private final CountryRepository countryRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${openai.api.key}")
    private String openAiApiKey;

    public String translateToEnglish(String originalText) {
        String url = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + openAiApiKey);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");

        Map<String, String> systemMessage = Map.of(
                "role", "system",
                "content", "You will be provided with statements, and your task is to convert them to standard English."
        );
        Map<String, String> userMessage = Map.of(
                "role", "user",
                "content", originalText
        );

        requestBody.put("messages", new Map[]{systemMessage, userMessage});
        requestBody.put("temperature", 0);
        requestBody.put("max_tokens", 2048);
        requestBody.put("top_p", 1);
        requestBody.put("frequency_penalty", 0);
        requestBody.put("presence_penalty", 0);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);


        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
        String translatedText = (String) ((Map) response.getBody().get("choices")).get("text");

        return translatedText.trim();
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
