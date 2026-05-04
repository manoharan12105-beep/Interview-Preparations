package me.mano.attendence_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
  private Long id;
  private String email;
  private String sprno;
  private String role; // ROLE_ADMIN or ROLE_STUDENT
  private Boolean active;
  private String firstName; // useful for frontend
  private String lastName;
}
