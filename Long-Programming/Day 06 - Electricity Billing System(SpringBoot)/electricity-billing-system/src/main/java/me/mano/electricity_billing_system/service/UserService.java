package me.mano.electricity_billing_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.mano.electricity_billing_system.entity.User;
import me.mano.electricity_billing_system.repo.UserRepo;

@Service
public class UserService {
  
  @Autowired
  private UserRepo userRepo;

  // User registration
  public User registerUser(User user) {
    if(userRepo.findByEmail(user.getEmail()) != null) {
      throw new RuntimeException("Email already exists");
    }
    return userRepo.save(user);
  }

  // User login
  public User login (String email, String password) {
    User user = userRepo.findByEmail(email);
    if(user == null || !user.getPassword().equals(password)) {
      throw new RuntimeException("Invalid email or password");
    }
    return user;
  }

  public User getUserById(Long id) {
    return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
  }
}
