package me.mano.attendence_management_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import me.mano.attendence_management_system.entity.UserEntity;
import me.mano.attendence_management_system.repo.UserRepo;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private me.mano.attendence_management_system.repo.StudentRepo studentRepo;

    @Autowired
    private me.mano.attendence_management_system.repo.SystemSettingsRepo systemSettingsRepo;

    @Override
    public void run(String... args) throws Exception {
        // Create a default admin user if none exists
        if (userRepo.findByEmail("admin@example.com").isEmpty()) {

            // Satisfy the foreign key constraint by creating a student record for the admin
            me.mano.attendence_management_system.entity.StudentEntity adminStudent = new me.mano.attendence_management_system.entity.StudentEntity();
            adminStudent.setSprno("ADMIN-001");
            adminStudent.setFirstName("System");
            adminStudent.setLastName("Admin");
            if (!studentRepo.existsById("ADMIN-001")) {
                studentRepo.save(adminStudent);
            }

            UserEntity admin = new UserEntity();
            admin.setEmail("admin@example.com");
            admin.setPassword("Admin123_$");
            admin.setSprno("ADMIN-001");
            admin.setRole("ROLE_ADMIN");
            admin.setActive(true);
            userRepo.save(admin);

            System.out.println("=========================================================");
            System.out.println("Default Admin Account Created!");
            System.out.println("Email: admin@example.com");
            System.out.println("Password: Admin123_$");
            System.out.println("=========================================================");
        }

        if (systemSettingsRepo.findById("ATTENDANCE_TIME").isEmpty()) {
            me.mano.attendence_management_system.entity.SystemSettingsEntity settings = new me.mano.attendence_management_system.entity.SystemSettingsEntity();
            settings.setId("ATTENDANCE_TIME");
            settings.setDefaultStartTime(java.time.LocalTime.of(9, 0));
            settings.setDefaultEndTime(java.time.LocalTime.of(9, 45));
            systemSettingsRepo.save(settings);
            System.out.println("Default attendance timings seeded (09:00 - 09:45)");
        }
    }
}
