package me.mano.electricity_billing_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.mano.electricity_billing_system.dto.UserDTO;
import me.mano.electricity_billing_system.entity.User;
import me.mano.electricity_billing_system.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  // User registration endpoint
  @PostMapping("/register")
  public User registerUser(@RequestBody User user) {
    return userService.registerUser(user);
  }

  // User login endpoint
  @PostMapping("/login")
  public User login(@RequestBody UserDTO userDTO) {
    return userService.login(userDTO.getEmail(), userDTO.getPassword());
  }

  // Get user details by ID
  @GetMapping("/{id}")
  public User getUserById(@PathVariable Long id) {
    return userService.getUserById(id);
  }
}

/*

  /register - POST - Register a new user
    {
    "name": "Syed Hassin",
    "email": "Syed@gmail.com",
    "password": "maddy123",
    "phoneNumber": "6384949432",
    "address": "11/43, south street, anna nagar, madurai-96",
    "role": "Customer",
    "registrationDate": "2026-04-21"
  }

  /login - POST - User login
    {
    "email": "Syed@gmail.com",
    "password": "maddy123"
  }

  /users/{id} - GET - Get user details by ID
    {
    "id": 1
  }


*/
