package me.mano.attendence_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import me.mano.attendence_management_system.dto.SettingsDTO;
import me.mano.attendence_management_system.entity.SystemSettingsEntity;
import me.mano.attendence_management_system.repo.SystemSettingsRepo;
import me.mano.attendence_management_system.entity.HolidayEntity;
import me.mano.attendence_management_system.repo.HolidayRepo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/settings")
public class SettingsController {

    @Autowired
    private SystemSettingsRepo settingsRepo;

    @Autowired
    private HolidayRepo holidayRepo;

    @GetMapping("/attendance-time")
    public ResponseEntity<Map<String, String>> getAttendanceTime() {
        SystemSettingsEntity settings = settingsRepo.findById("ATTENDANCE_TIME").orElse(null);
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(9, 45);

        if (settings != null) {
            LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));
            if (settings.getOverrideDate() != null && settings.getOverrideDate().equals(today)) {
                start = settings.getOverrideStartTime();
                end = settings.getOverrideEndTime();
            } else {
                start = settings.getDefaultStartTime();
                end = settings.getDefaultEndTime();
            }
        }

        Map<String, String> response = new HashMap<>();
        response.put("startTime", start.toString());
        response.put("endTime", end.toString());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/attendance-time")
    public ResponseEntity<?> updateAttendanceTime(@RequestBody SettingsDTO dto) {
        SystemSettingsEntity settings = settingsRepo.findById("ATTENDANCE_TIME").orElse(new SystemSettingsEntity());
        settings.setId("ATTENDANCE_TIME");

        if (dto.isForTodayOnly()) {
            settings.setOverrideDate(LocalDate.now(ZoneId.of("Asia/Kolkata")));
            settings.setOverrideStartTime(dto.getStartTime());
            settings.setOverrideEndTime(dto.getEndTime());
        } else {
            settings.setDefaultStartTime(dto.getStartTime());
            settings.setDefaultEndTime(dto.getEndTime());
            // Clear override if setting permanent
            settings.setOverrideDate(null);
            settings.setOverrideStartTime(null);
            settings.setOverrideEndTime(null);
        }

        settingsRepo.save(settings);
        return ResponseEntity.ok().body(Map.of("message", "Attendance duration updated successfully"));
    }

    @GetMapping("/holidays")
    public ResponseEntity<List<String>> getHolidays(@RequestParam int year, @RequestParam int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        List<String> holidays = holidayRepo.findByDateBetween(start, end).stream()
                .map(h -> h.getDate().toString())
                .collect(Collectors.toList());
        return ResponseEntity.ok(holidays);
    }

    @PostMapping("/holidays/{date}")
    public ResponseEntity<?> addHoliday(@PathVariable String date) {
        LocalDate d = LocalDate.parse(date);
        holidayRepo.save(new HolidayEntity(d));
        return ResponseEntity.ok().body(Map.of("message", "Holiday added successfully"));
    }

    @DeleteMapping("/holidays/{date}")
    public ResponseEntity<?> removeHoliday(@PathVariable String date) {
        LocalDate d = LocalDate.parse(date);
        holidayRepo.deleteById(d);
        return ResponseEntity.ok().body(Map.of("message", "Holiday removed successfully"));
    }
}
