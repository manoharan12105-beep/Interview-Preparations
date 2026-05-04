package me.mano.attendence_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
  private String sprno;
  private String firstName;
  private String lastName;
  private String email;
  private String password; // used when creating/updating
}
