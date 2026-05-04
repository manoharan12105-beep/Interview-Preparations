package me.mano.attendence_management_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.mano.attendence_management_system.entity.HolidayEntity;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface HolidayRepo extends JpaRepository<HolidayEntity, LocalDate> {
    List<HolidayEntity> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
