package se331.olympicsbackend.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SportDTO {
    @JsonProperty("name")
    private String sportName;
    @JsonProperty("gold")
    private int gold;
    @JsonProperty("silver")
    private  int silver;
    @JsonProperty("bronze")
    private int  bronze;
    @JsonProperty("total")
    private int total;
}
