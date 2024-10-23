package se331.olympicsbackend.rest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor @Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude

    long id;
    String flag;
    String countryName;
    String description;

    @OneToMany(mappedBy = "country")
    @Builder.Default
    List<Sport> sports = new ArrayList<>();
}
