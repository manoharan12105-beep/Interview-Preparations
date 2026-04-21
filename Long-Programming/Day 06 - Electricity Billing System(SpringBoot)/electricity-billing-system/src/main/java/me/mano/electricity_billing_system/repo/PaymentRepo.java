package me.mano.electricity_billing_system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import me.mano.electricity_billing_system.entity.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
  List<Payment> findByBillId(Long billId);
}
