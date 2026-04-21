package me.mano.electricity_billing_system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import me.mano.electricity_billing_system.entity.Billing;

@Repository
public interface BillingRepo extends JpaRepository<Billing, Long> {
  List<Billing> findByUserId(Long userId);
  List<Billing> findByMeterId(Long meterId);
}
