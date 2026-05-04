package me.mano.attendence_management_system.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import me.mano.attendence_management_system.entity.UserEntity;
import me.mano.attendence_management_system.repo.UserRepo;

import java.io.IOException;
import java.util.Optional;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private me.mano.attendence_management_system.repo.StudentRepo studentRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        
        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
        String email = oauthUser.getAttribute("email");

        Optional<UserEntity> userOpt = userRepo.findByEmail(email);

        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            
            // Safety: Ensure user has a sprno if missing
            if (user.getSprno() == null || user.getSprno().trim().isEmpty()) {
                String newSprno = "GGL-" + System.currentTimeMillis();
                user.setSprno(newSprno);
                userRepo.save(user);
                
                // Also create student entity if missing
                if (!studentRepo.existsById(newSprno)) {
                    me.mano.attendence_management_system.entity.StudentEntity newStudent = new me.mano.attendence_management_system.entity.StudentEntity();
                    newStudent.setSprno(newSprno);
                    String oauthName = oauthUser.getAttribute("name");
                    String fName = "Google";
                    String lName = "User";
                    if (oauthName != null && oauthName.contains(" ")) {
                        fName = oauthName.substring(0, oauthName.indexOf(" "));
                        lName = oauthName.substring(oauthName.indexOf(" ") + 1);
                    } else if (oauthName != null) {
                        fName = oauthName;
                    }
                    
                    newStudent.setFirstName(fName);
                    newStudent.setLastName(lName);
                    studentRepo.save(newStudent);
                }
            }

            if ("ROLE_ADMIN".equals(user.getRole())) {
                response.sendRedirect("/admin.html");
            } else {
                response.sendRedirect("/student.html");
            }
        } else {
            // Auto-register new Google user as a Student
            String name = oauthUser.getAttribute("name");
            String firstName = name != null ? name : "Google";
            String lastName = "User";
            if (name != null && name.contains(" ")) {
                firstName = name.substring(0, name.indexOf(" "));
                lastName = name.substring(name.indexOf(" ") + 1);
            }
            
            String newSprno = "GGL-" + System.currentTimeMillis();
            
            me.mano.attendence_management_system.entity.StudentEntity newStudent = new me.mano.attendence_management_system.entity.StudentEntity();
            newStudent.setSprno(newSprno);
            newStudent.setFirstName(firstName);
            newStudent.setLastName(lastName);
            studentRepo.save(newStudent);
            
            UserEntity newUser = new UserEntity();
            newUser.setEmail(email);
            newUser.setSprno(newSprno);
            newUser.setPassword("google_oauth2_managed");
            newUser.setRole("ROLE_STUDENT");
            newUser.setActive(true);
            userRepo.save(newUser);
            
            response.sendRedirect("/student.html");
        }
    }
}
