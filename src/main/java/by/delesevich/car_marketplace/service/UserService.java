package by.delesevich.car_marketplace.service;

import by.delesevich.car_marketplace.entity.User;
import by.delesevich.car_marketplace.entity.UserPage;
import org.springframework.data.domain.Page;

public interface UserService {

  Page<User> findAll(UserPage userPage);

  boolean saveUser (User user);

}
