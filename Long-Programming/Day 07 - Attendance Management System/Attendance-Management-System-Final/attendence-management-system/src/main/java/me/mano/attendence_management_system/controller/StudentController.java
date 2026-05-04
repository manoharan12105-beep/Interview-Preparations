package me.mano.attendence_management_system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import me.mano.attendence_management_system.dto.StudentDTO;
import me.mano.attendence_management_system.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody StudentDTO dto) {
        try {
            return ResponseEntity.ok(studentService.addStudent(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{sprno}")
    public ResponseEntity<?> updateStudent(@PathVariable String sprno, @RequestBody StudentDTO dto) {
        try {
            return ResponseEntity.ok(studentService.updateStudent(sprno, dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchStudents(@RequestParam String q) {
        return ResponseEntity.ok(studentService.searchStudents(q));
    }

    @DeleteMapping("/{sprno}")
    public ResponseEntity<?> deleteStudent(@PathVariable String sprno) {
        studentService.deleteStudent(sprno);
        return ResponseEntity.ok().build();
    }
}
