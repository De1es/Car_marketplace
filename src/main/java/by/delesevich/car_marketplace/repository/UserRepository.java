package by.delesevich.car_marketplace.repository;

import by.delesevich.car_marketplace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query(value = "select u from User u where u.login = :login")
  User findByLogin (String login);
}
