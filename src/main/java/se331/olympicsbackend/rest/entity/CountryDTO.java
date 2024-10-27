package se331.olympicsbackend.rest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {
    private long id;
    private String flag;
    private String countryName;

    // These fields align with Medal entity attributes.
    private int gold;
    private int silver;
    private int bronze;
    private int totalMedals;
    private int rank;
    private int totalRank;

}
