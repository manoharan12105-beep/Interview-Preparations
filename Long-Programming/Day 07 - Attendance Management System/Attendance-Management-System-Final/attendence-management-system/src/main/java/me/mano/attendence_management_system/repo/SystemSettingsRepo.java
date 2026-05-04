package me.mano.attendence_management_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.mano.attendence_management_system.entity.SystemSettingsEntity;

@Repository
public interface SystemSettingsRepo extends JpaRepository<SystemSettingsEntity, String> {
}
