package me.mano.electricity_billing_system.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import me.mano.electricity_billing_system.entity.Meter;

@Repository
public interface MeterRepo extends JpaRepository<Meter, Long> {
  List<Meter> findByUserId(Long userId);
  Optional<Meter> findByMeterNumber(String meterNumber);
}
