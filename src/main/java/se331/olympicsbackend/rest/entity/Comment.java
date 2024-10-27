package se331.olympicsbackend.rest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalText;   // Original comment by the user
    private String translatedText; // Translated to standard English

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;  // Associate the comment with a country

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
