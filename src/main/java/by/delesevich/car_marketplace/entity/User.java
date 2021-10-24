package by.delesevich.car_marketplace.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "u_id")
  private Long id;

  @Column(name = "u_login")
  private String login;

  @Column(name = "u_password")
  private String password;

  @Column(name = "u_role")
  private String role;

  @Column(name = "u_date_of_delete")
  private Timestamp dateOfDelete;

}
