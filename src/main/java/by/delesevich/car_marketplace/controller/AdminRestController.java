package by.delesevich.car_marketplace.controller;

import by.delesevich.car_marketplace.entity.lot.Lot;
import by.delesevich.car_marketplace.entity.lot.LotPage;
import by.delesevich.car_marketplace.entity.user.User;
import by.delesevich.car_marketplace.entity.user.UserPage;
import by.delesevich.car_marketplace.service.LotService;
import by.delesevich.car_marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminRestController {

  private final LotService lotService;
  private final UserService userService;

  @GetMapping("/lots")
  public ResponseEntity<Page<Lot>> findAllLots(LotPage lotPage) {
    return new ResponseEntity<>(lotService.findAll(lotPage), HttpStatus.OK);
  }

  @GetMapping("/users")
  public ResponseEntity<Page<User>> findAllUsers(UserPage userPage) {
    return new ResponseEntity<>(userService.findAll(userPage), HttpStatus.OK);
  }

  @PostMapping("/users")
  public ResponseEntity<Boolean> saveUser (User user) {
    return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
  }



}
