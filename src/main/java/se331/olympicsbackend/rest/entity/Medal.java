package se331.olympicsbackend.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medal", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"country_id", "sport_id"})
})
public class Medal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private long id ;
    @JsonProperty("gold_medals")
    private int gold;
    @JsonProperty("silver_medals")
    private int silver;
    @JsonProperty("bronze_medals")
    private int bronze;
    @JsonProperty("total_medals")
    private int totalMedals;
    @JsonProperty("rank")
    private int ranking;
    @JsonProperty("rank_total_medals")
    private int totalRank;

    @ToString.Exclude
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_id", referencedColumnName = "id")
    Sport sport;


}
