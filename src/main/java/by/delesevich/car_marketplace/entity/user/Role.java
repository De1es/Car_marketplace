package by.delesevich.car_marketplace.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {

  public Role(String name) {
    this.name = name;
  }

  @Id
  @Column(name = "r_id")
  private Long id;

  @Column(name = "r_name")
  private String name;

  @Override
  public String getAuthority() {
    return getName();
  }
}
