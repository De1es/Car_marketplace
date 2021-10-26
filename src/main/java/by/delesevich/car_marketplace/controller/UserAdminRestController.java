package by.delesevich.car_marketplace.controller;

import by.delesevich.car_marketplace.dto.UserDtoForAdmin;
import by.delesevich.car_marketplace.entity.user.User;
import by.delesevich.car_marketplace.entity.user.UserPage;
import by.delesevich.car_marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class UserAdminRestController {

  private final UserService userService;

  @GetMapping("/")
  public ResponseEntity<Page<UserDtoForAdmin>> findAllUsers(UserPage userPage) {
    return new ResponseEntity<>(userService.findAll(userPage), HttpStatus.OK);
  }

  @PostMapping("/")
  public ResponseEntity<Boolean> saveUser(User user) {
    return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDtoForAdmin> findLotById(@PathVariable Long id) {
    return new ResponseEntity<>(new UserDtoForAdmin(userService.findById(id)), HttpStatus.OK);
  }


}
