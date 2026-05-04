package me.mano.attendence_management_system.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import me.mano.attendence_management_system.entity.AttendanceEntity;
import me.mano.attendence_management_system.entity.StudentEntity;

public interface AttendanceRepo extends JpaRepository<AttendanceEntity, Long> {
    Optional<AttendanceEntity> findByStudentAndDate(StudentEntity student, LocalDate date);
    List<AttendanceEntity> findByDate(LocalDate date);
    List<AttendanceEntity> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<AttendanceEntity> findByStudent(StudentEntity student);
    List<AttendanceEntity> findByStudentOrderByDateDesc(StudentEntity student);
}
