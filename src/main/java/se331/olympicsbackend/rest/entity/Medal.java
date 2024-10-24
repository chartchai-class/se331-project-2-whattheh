package se331.olympicsbackend.rest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Medal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    long id ;
    @JsonProperty("gold_medals")
    int gold;
    @JsonProperty("silver_medals")
    int silver;
    @JsonProperty("bronze_medals")
    int bronze;
    @JsonProperty("total_medals")
    int totalMedals;
    @JsonProperty("rank")
    int ranking;
    @JsonProperty("rank_total_medals")
    int totalRank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_id")
    Sport sport;


}
