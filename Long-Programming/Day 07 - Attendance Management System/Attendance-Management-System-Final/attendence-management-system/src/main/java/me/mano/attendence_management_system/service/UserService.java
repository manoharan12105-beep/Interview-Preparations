package me.mano.attendence_management_system.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import me.mano.attendence_management_system.dto.UserDTO;
import me.mano.attendence_management_system.entity.UserEntity;
import me.mano.attendence_management_system.repo.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public UserDTO login(String email, String password) {
        Optional<UserEntity> userOpt = userRepo.findByEmail(email);
        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            if (user.getPassword().equals(password)) {
                UserDTO dto = new UserDTO();
                dto.setId(user.getId());
                dto.setEmail(user.getEmail());
                dto.setSprno(user.getSprno());
                dto.setRole(user.getRole());
                dto.setActive(user.isActive());
                if (user.getStudent() != null) {
                    dto.setFirstName(user.getStudent().getFirstName());
                    dto.setLastName(user.getStudent().getLastName());
                } else if ("ROLE_ADMIN".equals(user.getRole())) {
                    dto.setFirstName("Admin");
                    dto.setLastName("User");
                }
                return dto;
            }
        }
        throw new RuntimeException("Invalid email or password");
    }

    public UserDTO getUserByEmail(String email) {
        Optional<UserEntity> userOpt = userRepo.findByEmail(email);
        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setEmail(user.getEmail());
            dto.setSprno(user.getSprno());
            dto.setRole(user.getRole());
            dto.setActive(user.isActive());
            if (user.getStudent() != null) {
                dto.setFirstName(user.getStudent().getFirstName());
                dto.setLastName(user.getStudent().getLastName());
            } else if ("ROLE_ADMIN".equals(user.getRole())) {
                dto.setFirstName("Admin");
                dto.setLastName("User");
            }
            return dto;
        }
        throw new RuntimeException("User not found");
    }
}
