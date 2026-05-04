package me.mano.attendence_management_system.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import me.mano.attendence_management_system.service.AttedanceService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttedanceService attendanceService;

    @PostMapping("/mark")
    public ResponseEntity<?> markAttendance(@RequestBody Map<String, String> payload) {
        try {
            String sprno = payload.get("studentSprno");
            String studentName = payload.get("studentName");
            String loggedInEmail = payload.get("loggedInEmail");
            String resultName = attendanceService.markAttendance(sprno, studentName, loggedInEmail);
            return ResponseEntity.ok().body(Map.of("message", "Attendance marked successfully for " + resultName + "!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/daily")
    public ResponseEntity<?> getDaily(@RequestParam String date) {
        return ResponseEntity.ok(attendanceService.getDailyAttendance(LocalDate.parse(date)));
    }

    @GetMapping("/weekly")
    public ResponseEntity<?> getWeekly(@RequestParam String start, @RequestParam String end) {
        return ResponseEntity.ok(attendanceService.getWeeklyAttendance(LocalDate.parse(start), LocalDate.parse(end)));
    }

    @GetMapping("/monthly-report")
    public ResponseEntity<?> getMonthlyReport(@RequestParam int year, @RequestParam int month) {
        return ResponseEntity.ok(attendanceService.getMonthlyAttendanceReport(YearMonth.of(year, month)));
    }

    @GetMapping("/monthly-graph")
    public ResponseEntity<?> getMonthlyGraph(@RequestParam int year, @RequestParam int month) {
        return ResponseEntity.ok(attendanceService.getMonthlyAttendanceGraphData(YearMonth.of(year, month)));
    }

    @GetMapping("/my-stats")
    public ResponseEntity<?> getMyStats(@RequestParam String sprno) {
        try {
            return ResponseEntity.ok(attendanceService.getStudentStats(sprno));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/my-history")
    public ResponseEntity<?> getMyHistory(@RequestParam String sprno, @RequestParam(defaultValue = "5") int limit) {
        try {
            return ResponseEntity.ok(attendanceService.getStudentHistory(sprno, limit));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/class-stats")
    public ResponseEntity<?> getClassStats() {
        return ResponseEntity.ok(attendanceService.getClassStats());
    }
}
