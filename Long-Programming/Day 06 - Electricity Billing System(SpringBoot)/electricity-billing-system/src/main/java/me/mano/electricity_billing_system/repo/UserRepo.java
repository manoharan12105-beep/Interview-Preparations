package me.mano.electricity_billing_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import me.mano.electricity_billing_system.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

  User findByEmail(String email);
  
}
