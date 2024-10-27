package se331.olympicsbackend.rest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {
    private long id;
    private String flag;
    private String countryName;

    // These fields align with Medal entity attributes.
    private int gold_medals;
    private int silver_medals;
    private int bronze_medals;
    private int total_medals;
    private int ranking;
    private int totalRank;
    List<SportDTO> sports ;
}
