package me.mano.attendence_management_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false, unique = true)
  private String sprno;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String role; // "ROLE_ADMIN" or "ROLE_STUDENT"

  @Column(nullable = false)
  private boolean active = true;

  @OneToOne
  @JoinColumn(name = "sprno", referencedColumnName = "sprno", insertable = false, updatable = false, foreignKey = @jakarta.persistence.ForeignKey(jakarta.persistence.ConstraintMode.NO_CONSTRAINT))
  private StudentEntity student;
}