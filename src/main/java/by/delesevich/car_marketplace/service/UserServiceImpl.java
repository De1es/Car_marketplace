package by.delesevich.car_marketplace.service;

import by.delesevich.car_marketplace.entity.user.Role;
import by.delesevich.car_marketplace.entity.user.User;
import by.delesevich.car_marketplace.entity.user.UserPage;
import by.delesevich.car_marketplace.repository.UserRepository;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Data
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public Page<User> findAll(UserPage userPage) {
    Sort sort = Sort.by(userPage.getSortDirection(), userPage.getSortBy());
    Pageable pageable = PageRequest.of(userPage.getPageNumber(), userPage.getPageSize(), sort);
    return userRepository.findAll(pageable);
  }

  @Override
  public boolean saveUser(User user) {
    User userFromBd = userRepository.findByLogin(user.getLogin());
    if (userFromBd != null){
      return false;
    }

    user.setRole(new Role("ROLE_USER"));
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    System.out.println(bCryptPasswordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return true;
  }

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    User user = userRepository.findByLogin(login);
    if (user == null){
      throw new UsernameNotFoundException("User not found");
    }
    return user;
  }
}
