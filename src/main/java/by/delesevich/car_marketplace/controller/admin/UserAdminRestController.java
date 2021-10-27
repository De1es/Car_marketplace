package by.delesevich.car_marketplace.controller.admin;

import by.delesevich.car_marketplace.config.SwaggerConfig;
import by.delesevich.car_marketplace.dto.UserDtoForAdmin;
import by.delesevich.car_marketplace.entity.user.User;
import by.delesevich.car_marketplace.entity.user.UserPage;
import by.delesevich.car_marketplace.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "LotAdminRestController", tags = {SwaggerConfig.ADMIN_USER_TAG})
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class UserAdminRestController {

  private final UserService userService;

  @GetMapping("/")
  @Operation(summary = "Find in db and return page of users (include all deleted lots)")
  public ResponseEntity<Page<UserDtoForAdmin>> findAllUsers(UserPage userPage) {
    return new ResponseEntity<>(userService.findAll(userPage), HttpStatus.OK);
  }

/*
  @PostMapping("/")
  @Operation(summary = "Save new user")
  public ResponseEntity<Boolean> saveUser(User user) {
    return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
  }
*/

  @GetMapping("/{id}")
  @Operation(summary = "Find in db and return user with this id")
  public ResponseEntity<UserDtoForAdmin> findLotById(@PathVariable @ApiParam(value = "Id of " +
      "finding user", example = "1") Long id) {
    return new ResponseEntity<>(new UserDtoForAdmin(userService.findById(id)), HttpStatus.OK);
  }


}
