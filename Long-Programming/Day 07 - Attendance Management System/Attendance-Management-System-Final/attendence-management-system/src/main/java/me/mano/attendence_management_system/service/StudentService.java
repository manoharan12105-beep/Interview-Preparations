package me.mano.attendence_management_system.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import me.mano.attendence_management_system.dto.StudentDTO;
import me.mano.attendence_management_system.entity.StudentEntity;
import me.mano.attendence_management_system.entity.UserEntity;
import me.mano.attendence_management_system.repo.StudentRepo;
import me.mano.attendence_management_system.repo.UserRepo;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private UserRepo userRepo;

    @Transactional
    public StudentDTO addStudent(StudentDTO dto) {
        if (dto.getSprno() != null) {
            dto.setSprno(dto.getSprno().toUpperCase());
        }
        if (studentRepo.existsById(dto.getSprno())) {
            throw new RuntimeException("Student with SPRNO already exists");
        }
        
        String emailToUse = (dto.getEmail() != null && !dto.getEmail().trim().isEmpty()) 
                            ? dto.getEmail() 
                            : dto.getSprno() + "@student.edu";

        if (userRepo.findByEmail(emailToUse).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        StudentEntity student = new StudentEntity();
        student.setSprno(dto.getSprno());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        studentRepo.save(student);

        UserEntity user = new UserEntity();
        user.setEmail(emailToUse);
        user.setSprno(dto.getSprno());
        user.setPassword(dto.getPassword() != null ? dto.getPassword() : "password123");
        user.setRole("ROLE_STUDENT");
        user.setActive(true);
        userRepo.save(user);

        return dto;
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepo.findAll().stream().map(student -> {
            Optional<UserEntity> userOpt = userRepo.findBySprno(student.getSprno());
            String email = userOpt.map(UserEntity::getEmail).orElse("");
            return new StudentDTO(student.getSprno(), student.getFirstName(), student.getLastName(), email, null);
        }).collect(Collectors.toList());
    }

    public List<StudentDTO> searchStudents(String query) {
        if (query == null || query.trim().isEmpty()) {
            return studentRepo.findAll().stream()
                .map(s -> new StudentDTO(s.getSprno(), s.getFirstName(), s.getLastName(), null, null))
                .collect(Collectors.toList());
        }
        return studentRepo.searchByName(query.trim()).stream()
            .map(s -> new StudentDTO(s.getSprno(), s.getFirstName(), s.getLastName(), null, null))
            .collect(Collectors.toList());
    }

    @Transactional
    public StudentDTO updateStudent(String sprno, StudentDTO dto) {
        sprno = sprno.toUpperCase();
        StudentEntity student = studentRepo.findById(sprno)
            .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        studentRepo.save(student);

        Optional<UserEntity> userOpt = userRepo.findBySprno(sprno);
        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            if (dto.getEmail() != null) user.setEmail(dto.getEmail());
            if (dto.getPassword() != null && !dto.getPassword().isEmpty()) user.setPassword(dto.getPassword());
            userRepo.save(user);
        }

        return dto;
    }

    @Transactional
    public void deleteStudent(String sprno) {
        sprno = sprno.toUpperCase();
        userRepo.findBySprno(sprno).ifPresent(userRepo::delete);
        studentRepo.deleteById(sprno);
    }
}
