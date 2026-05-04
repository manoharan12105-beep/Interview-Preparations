package me.mano.attendence_management_system.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDTO {
  private Long id;
  private String sprno;
  private String studentName;
  private LocalDate date;
  private boolean present;
  private String attendanceMarkedBy;
  private LocalTime markedAt;
}
