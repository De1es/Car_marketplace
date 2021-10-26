package by.delesevich.car_marketplace.service;

import by.delesevich.car_marketplace.dto.UserDtoForAdmin;
import by.delesevich.car_marketplace.entity.user.User;
import by.delesevich.car_marketplace.entity.user.UserPage;
import org.springframework.data.domain.Page;

public interface UserService {

  User findById(Long id);

  Page<UserDtoForAdmin> findAll(UserPage userPage);

  boolean saveUser (User user);

}
