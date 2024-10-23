package se331.olympicsbackend.rest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    int id;
    String sportName;

    @ManyToOne
    Country country;

    @OneToMany(mappedBy = "sport", cascade = CascadeType.ALL)
    private List<Medal> medals = new ArrayList<>();
}
