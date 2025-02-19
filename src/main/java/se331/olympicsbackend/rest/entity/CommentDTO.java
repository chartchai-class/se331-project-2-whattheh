package se331.olympicsbackend.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private String comment;
    private Integer userId;
    private Integer countryId;
}
