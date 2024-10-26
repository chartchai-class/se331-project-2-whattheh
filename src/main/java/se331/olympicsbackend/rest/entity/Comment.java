package se331.olympicsbackend.rest.entity;

import jakarta.persistence.*;
import lombok.*;
import se331.olympicsbackend.rest.security.user.User;


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
    //user
    @ManyToOne
    private User user;
    //country
    @ManyToOne
    private Country country;
}
