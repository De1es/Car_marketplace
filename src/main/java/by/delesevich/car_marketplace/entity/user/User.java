package by.delesevich.car_marketplace.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "u_id")
  private Long id;

  @Column(name = "u_login")
  private String login;

  @Column(name = "u_password")
  private String password;

  @ManyToOne
  @JoinColumn(name = "u_role")
  private Role role;

  @Column(name = "u_date_of_delete")
  private Timestamp dateOfDelete;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> collection = new ArrayList<>();
    collection.add(role);
    return collection;
  }

  public User(Long id) {
    this.id = id;
  }

  public User(Long id, String login, Role role, Timestamp dateOfDelete) {
    this.id = id;
    this.login = login;
    this.role = role;
    this.dateOfDelete = dateOfDelete;
  }

  @Override
  public String getUsername() {
    return login;
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
    return true;
  }
}
