package se331.olympicsbackend.rest.entity;

import jakarta.persistence.*;
import lombok.*;
import se331.olympicsbackend.rest.security.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    long  id;
    String comment;
    Timestamp timestamp;
    //user

    //country
}
