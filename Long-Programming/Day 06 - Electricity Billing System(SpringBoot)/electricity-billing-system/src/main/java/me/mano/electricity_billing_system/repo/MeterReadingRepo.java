package me.mano.electricity_billing_system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import me.mano.electricity_billing_system.entity.MeterReading;

@Repository
public interface MeterReadingRepo extends JpaRepository<MeterReading, Long> {
  List<MeterReading> findByMeterId(Long meterId);
}
