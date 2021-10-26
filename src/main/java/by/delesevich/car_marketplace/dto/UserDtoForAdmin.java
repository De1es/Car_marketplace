package by.delesevich.car_marketplace.dto;

import by.delesevich.car_marketplace.entity.user.Role;
import by.delesevich.car_marketplace.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoForAdmin {

  @PositiveOrZero (message = "Id should be positive number")
  private Long id;

  private String login;

  private String role;

  private Timestamp dateOfDelete;

  public UserDtoForAdmin(User user) {
    this.id = user.getId();
    this.login = user.getLogin();
    this.role = user.getRole().getName();
    this.dateOfDelete = user.getDateOfDelete();
  }

  public User toUser () {
    return new User(id, login, new Role(role), dateOfDelete);
  }
}
