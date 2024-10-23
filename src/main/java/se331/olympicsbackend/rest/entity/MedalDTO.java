
package se331.olympicsbackend.rest.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedalDTO {
    @JsonProperty("gold_medals")
    Integer gold;
    @JsonProperty("silver_medals")
    Integer silver;
    @JsonProperty("bronze_medals")
    Integer bronze;
    @JsonProperty("total_medals")
    Integer totalMedals;
    @JsonProperty("rank")
    Integer ranking;
    @JsonProperty("rank_total_medals")
    Integer totalRank;
}
