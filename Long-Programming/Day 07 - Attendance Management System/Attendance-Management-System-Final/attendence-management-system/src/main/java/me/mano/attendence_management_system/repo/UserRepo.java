package me.mano.attendence_management_system.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import me.mano.attendence_management_system.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findBySprno(String sprno);
}
