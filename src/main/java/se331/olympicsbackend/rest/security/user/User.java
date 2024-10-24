package se331.olympicsbackend.rest.security.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import se331.lab_new.entity.Organizer;
import se331.olympicsbackend.rest.entity.Comment;
import se331.olympicsbackend.rest.security.token.Token;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String username;
  @Column(unique = true)
  private String email;
  private String password;
  private Boolean enabled;

  @Enumerated(EnumType.STRING)
  @ElementCollection
  @Builder.Default
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<Role> roles = new ArrayList<>();



  @OneToMany(mappedBy = "user")
  @Builder.Default
  private List<Token> tokens;
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

//  @OneToOne(mappedBy = "user")
//  Organizer organizer;
}
