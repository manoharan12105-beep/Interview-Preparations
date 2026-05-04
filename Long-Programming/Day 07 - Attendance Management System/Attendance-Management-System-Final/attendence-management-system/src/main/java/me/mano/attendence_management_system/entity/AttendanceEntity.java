package me.mano.attendence_management_system.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "attendances", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"student_id", "date"})
})
@Data
public class AttendanceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "student_id", nullable = false, referencedColumnName = "sprno")
  private StudentEntity student;

  @Column(nullable = false)
  private LocalDate date;

  @Column(nullable = false)
  private boolean present;

  @Column(name = "attendance_marked_by", nullable = false)
  private String attendanceMarkedBy; // Email of the user who marked this attendance

  @Column(name = "marked_at")
  private LocalTime markedAt; // Time when attendance was marked
}