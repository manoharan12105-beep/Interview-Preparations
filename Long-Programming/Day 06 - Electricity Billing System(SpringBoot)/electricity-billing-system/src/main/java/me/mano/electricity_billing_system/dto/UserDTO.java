package me.mano.electricity_billing_system.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
  
  private Long id;
  private String name;
  private String email;
  private String password;
  private String phoneNumber;
  private String address;
  private String role;
  private LocalDate registrationDate;
}
