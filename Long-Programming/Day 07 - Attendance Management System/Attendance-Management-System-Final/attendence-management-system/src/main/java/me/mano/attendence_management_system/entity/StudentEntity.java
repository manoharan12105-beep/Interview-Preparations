package me.mano.attendence_management_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "students")
@Entity
@Data
public class StudentEntity {
  @Id
  private String sprno;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;
}
