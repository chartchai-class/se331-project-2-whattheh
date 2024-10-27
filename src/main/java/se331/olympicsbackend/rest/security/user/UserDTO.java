package se331.olympicsbackend.rest.security.user;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {
    Integer id;
    String username;
    String email;
    String password;
    Boolean enabled;
    List<String> roles=new ArrayList<>();


}
