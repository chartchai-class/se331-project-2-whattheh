package se331.olympicsbackend.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedalCountryDTO {

    @JsonProperty("flag_url")
    String flag;
    @JsonProperty("name")
    String countryName;

    @JsonProperty("description")
    String countryDescription;

}
