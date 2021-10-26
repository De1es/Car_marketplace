package by.delesevich.car_marketplace.service;

import by.delesevich.car_marketplace.entity.user.User;
import by.delesevich.car_marketplace.entity.user.UserPage;
import org.springframework.data.domain.Page;

public interface UserService {

  Page<User> findAll(UserPage userPage);

  boolean saveUser (User user);

}
