package me.mano.attendence_management_system.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import me.mano.attendence_management_system.dto.AttendanceDTO;
import me.mano.attendence_management_system.dto.MonthlyReportDTO;
import me.mano.attendence_management_system.entity.AttendanceEntity;
import me.mano.attendence_management_system.entity.StudentEntity;
import me.mano.attendence_management_system.entity.SystemSettingsEntity;
import me.mano.attendence_management_system.repo.AttendanceRepo;
import me.mano.attendence_management_system.repo.StudentRepo;
import me.mano.attendence_management_system.repo.SystemSettingsRepo;
import me.mano.attendence_management_system.entity.HolidayEntity;
import me.mano.attendence_management_system.repo.HolidayRepo;

@Service
public class AttedanceService {

    @Autowired
    private AttendanceRepo attendanceRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private SystemSettingsRepo systemSettingsRepo;

    @Autowired
    private HolidayRepo holidayRepo;

    public String markAttendance(String studentSprno, String studentName, String loggedInEmail) {
        if (studentSprno != null) {
            studentSprno = studentSprno.trim().toUpperCase();
        }
        if (studentName != null) {
            studentName = studentName.trim();
        }

        LocalTime now = LocalTime.now(ZoneId.of("Asia/Kolkata"));
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(9, 45);

        SystemSettingsEntity settings = systemSettingsRepo.findById("ATTENDANCE_TIME").orElse(null);
        if (settings != null) {
            if (settings.getOverrideDate() != null && settings.getOverrideDate().equals(today)) {
                start = settings.getOverrideStartTime();
                end = settings.getOverrideEndTime();
            } else {
                start = settings.getDefaultStartTime();
                end = settings.getDefaultEndTime();
            }
        }

        if (now.isBefore(start) || now.isAfter(end)) {
            throw new RuntimeException("Attendance can only be marked between " + start + " and " + end);
        }

        // Look up student by SPR number first, then by name
        StudentEntity student = null;
        if (studentSprno != null && !studentSprno.isEmpty()) {
            student = studentRepo.findById(studentSprno).orElse(null);
        }
        if (student == null && studentName != null && !studentName.isEmpty()) {
            student = studentRepo.findByFullNameIgnoreCase(studentName).orElse(null);
        }
        if (student == null) {
            throw new RuntimeException("Student not found");
        }

        Optional<AttendanceEntity> existing = attendanceRepo.findByStudentAndDate(student, today);
        if (existing.isPresent()) {
            throw new RuntimeException("Attendance already marked for today");
        }

        AttendanceEntity attendance = new AttendanceEntity();
        attendance.setStudent(student);
        attendance.setDate(today);
        attendance.setPresent(true);
        attendance.setAttendanceMarkedBy(loggedInEmail);
        attendance.setMarkedAt(now);

        attendanceRepo.save(attendance);
        return student.getFirstName() + " " + student.getLastName();
    }

    public List<AttendanceDTO> getDailyAttendance(LocalDate date) {
        return attendanceRepo.findByDate(date).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<AttendanceDTO> getWeeklyAttendance(LocalDate startOfWeek, LocalDate endOfWeek) {
        return attendanceRepo.findByDateBetween(startOfWeek, endOfWeek).stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<MonthlyReportDTO> getMonthlyAttendanceReport(YearMonth month) {
        LocalDate startOfMonth = month.atDay(1);
        LocalDate endOfMonth = month.atEndOfMonth();
        List<AttendanceEntity> attendances = attendanceRepo.findByDateBetween(startOfMonth, endOfMonth);

        List<StudentEntity> allStudents = studentRepo.findAll();
        List<HolidayEntity> holidaysInMonth = holidayRepo.findByDateBetween(startOfMonth, endOfMonth);
        List<LocalDate> holidayDates = holidaysInMonth.stream().map(HolidayEntity::getDate)
                .collect(Collectors.toList());

        // Calculate max working days in month up to today if it's current month
        LocalDate cutoffDate = endOfMonth;
        if (YearMonth.now(ZoneId.of("Asia/Kolkata")).equals(month)) {
            cutoffDate = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        }

        int totalDays = 0;
        for (int i = 1; i <= cutoffDate.getDayOfMonth(); i++) {
            if (!holidayDates.contains(month.atDay(i))) {
                totalDays++;
            }
        }

        final int finalTotalDays = totalDays;

        return allStudents.stream().map(student -> {
            long presentCount = attendances.stream()
                    .filter(a -> a.getStudent().getSprno().equals(student.getSprno()) && a.isPresent()
                            && !holidayDates.contains(a.getDate()))
                    .count();
            double percentage = finalTotalDays == 0 ? 0 : ((double) presentCount / finalTotalDays) * 100;
            return new MonthlyReportDTO(student.getSprno(), student.getFirstName(), student.getLastName(),
                    (int) presentCount, percentage);
        }).collect(Collectors.toList());
    }

    public Map<String, Integer> getMonthlyAttendanceGraphData(YearMonth month) {
        LocalDate startOfMonth = month.atDay(1);
        LocalDate endOfMonth = month.atEndOfMonth();
        List<AttendanceEntity> attendances = attendanceRepo.findByDateBetween(startOfMonth, endOfMonth);

        Map<String, Integer> graphData = new HashMap<>();
        for (int i = 1; i <= month.lengthOfMonth(); i++) {
            LocalDate date = month.atDay(i);
            if (date.isAfter(LocalDate.now(ZoneId.of("Asia/Kolkata"))))
                break;
            long count = attendances.stream().filter(a -> a.getDate().equals(date) && a.isPresent()).count();
            graphData.put(date.toString(), (int) count);
        }
        return graphData;
    }

    public Map<String, Object> getStudentStats(String sprno) {
        StudentEntity student = studentRepo.findById(sprno.toUpperCase())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        YearMonth currentMonth = YearMonth.from(today);
        LocalDate startOfMonth = currentMonth.atDay(1);
        LocalDate endOfMonth = currentMonth.atEndOfMonth();

        // Check if marked today
        boolean todayMarked = attendanceRepo.findByStudentAndDate(student, today).isPresent();

        // Calculate attendance for current month (excluding holidays)
        List<HolidayEntity> holidays = holidayRepo.findByDateBetween(startOfMonth, endOfMonth);
        List<LocalDate> holidayDates = holidays.stream().map(HolidayEntity::getDate).collect(Collectors.toList());

        List<AttendanceEntity> monthAttendances = attendanceRepo.findByStudent(student).stream()
                .filter(a -> !a.getDate().isBefore(startOfMonth) && !a.getDate().isAfter(today) && a.isPresent()
                        && !holidayDates.contains(a.getDate()))
                .collect(Collectors.toList());

        int totalDays = 0;
        for (int i = 1; i <= today.getDayOfMonth(); i++) {
            if (!holidayDates.contains(currentMonth.atDay(i))) {
                totalDays++;
            }
        }

        double percentage = totalDays == 0 ? 0 : ((double) monthAttendances.size() / totalDays) * 100;

        Map<String, Object> stats = new HashMap<>();
        stats.put("todayMarked", todayMarked);
        stats.put("daysPresent", monthAttendances.size());
        stats.put("totalDays", totalDays);
        stats.put("percentage", Math.round(percentage * 10.0) / 10.0);
        return stats;
    }

    public List<AttendanceDTO> getStudentHistory(String sprno, int limit) {
        StudentEntity student = studentRepo.findById(sprno.toUpperCase())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return attendanceRepo.findByStudentOrderByDateDesc(student).stream()
                .limit(limit)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private AttendanceDTO mapToDTO(AttendanceEntity entity) {
        return new AttendanceDTO(
                entity.getId(),
                entity.getStudent().getSprno(),
                entity.getStudent().getFirstName() + " " + entity.getStudent().getLastName(),
                entity.getDate(),
                entity.isPresent(),
                entity.getAttendanceMarkedBy(),
                entity.getMarkedAt());
    }

    public Map<String, Object> getClassStats() {
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        YearMonth currentMonth = YearMonth.from(today);
        LocalDate startOfMonth = currentMonth.atDay(1);
        LocalDate endOfMonth = currentMonth.atEndOfMonth();

        // 1. Filter for students in the actual class (starts with 23CS)
        List<StudentEntity> classStudents = studentRepo.findAll().stream()
                .filter(s -> s.getSprno() != null && s.getSprno().toUpperCase().startsWith("23CS"))
                .collect(Collectors.toList());
        
        long totalStudents = classStudents.size();
        List<String> classSprnos = classStudents.stream().map(StudentEntity::getSprno).collect(Collectors.toList());

        // 2. Today's presence count (only for class students)
        long presentToday = attendanceRepo.findByDate(today).stream()
                .filter(a -> a.isPresent() && classSprnos.contains(a.getStudent().getSprno()))
                .count();

        // 3. Class Average Percentage for current month (excluding holidays)
        List<HolidayEntity> holidays = holidayRepo.findByDateBetween(startOfMonth, endOfMonth);
        List<LocalDate> holidayDates = holidays.stream().map(HolidayEntity::getDate).collect(Collectors.toList());

        int totalWorkingDays = 0;
        for (int i = 1; i <= today.getDayOfMonth(); i++) {
            if (!holidayDates.contains(currentMonth.atDay(i))) {
                totalWorkingDays++;
            }
        }

        double classAverage = 0;
        if (totalStudents > 0 && totalWorkingDays > 0) {
            // Fetch all present records for the month (only for class students)
            long totalPresencesInMonth = attendanceRepo.findByDateBetween(startOfMonth, today).stream()
                    .filter(a -> a.isPresent() && !holidayDates.contains(a.getDate()) && classSprnos.contains(a.getStudent().getSprno()))
                    .count();
            
            // Average = Total Present Days / (Total Students * Total Working Days)
            classAverage = (double) totalPresencesInMonth / (totalStudents * totalWorkingDays) * 100;
        }

        Map<String, Object> stats = new HashMap<>();
        stats.put("presentToday", presentToday);
        stats.put("totalStudents", totalStudents);
        stats.put("classAverage", Math.round(classAverage * 10.0) / 10.0);
        return stats;
    }
}
