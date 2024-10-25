package se331.olympicsbackend.rest.entity;

import jakarta.persistence.*;
import lombok.*;


import java.sql.Timestamp;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private long  id;
    private String comment;
    private  Timestamp timestamp;
    //user

    //country
}
