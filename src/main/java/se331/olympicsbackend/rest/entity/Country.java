package se331.olympicsbackend.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private long id;

    private String flag;
    private String countryName;

    @Lob  // Large Object annotation for long text
    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sport> sports = new ArrayList<>();

    @ToString.Exclude
    @JsonIgnore// Avoid circular reference in toString
    @OneToOne(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Medal medal;
}
