package by.delesevich.car_marketplace.service;

import by.delesevich.car_marketplace.dto.UserDtoForAdmin;
import by.delesevich.car_marketplace.entity.user.Role;
import by.delesevich.car_marketplace.entity.user.User;
import by.delesevich.car_marketplace.entity.user.UserPage;
import by.delesevich.car_marketplace.repository.UserRepository;
import lombok.Data;
import org.springframework.data.domain.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public User findById(Long id) {
    Optional<User> optional = userRepository.findById(id);
    if (optional.isEmpty()){
      throw new IllegalArgumentException("User with the given id was not found");
    }
    return optional.get();

  }

  @Override
  public Page<UserDtoForAdmin> findAll(UserPage userPage) {
    Sort sort = Sort.by(userPage.getSortDirection(), userPage.getSortBy().toString());
    Pageable pageable = PageRequest.of(userPage.getPageNumber(), userPage.getPageSize(), sort);
    Page <User> page = userRepository.findAll(pageable);
    List<UserDtoForAdmin> list =
        page.getContent()
            .stream()
            .map(UserDtoForAdmin::new)
            .collect(Collectors.toList());
    return new PageImpl<>(list, pageable, page.getTotalElements());
  }

  @Override
  public boolean saveUser(User user) {
    User userFromBd = userRepository.findByLogin(user.getLogin());
    if (userFromBd != null){
      return false;
    }
    user.setRole(new Role("ROLE_USER"));
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
