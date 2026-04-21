package me.mano.electricity_billing_system.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.mano.electricity_billing_system.entity.Billing;
import me.mano.electricity_billing_system.entity.Meter;
import me.mano.electricity_billing_system.entity.User;
import me.mano.electricity_billing_system.repo.BillingRepo;

@Service
public class BillingService {

  @Autowired
  private BillingRepo billingRepo;

  @Autowired
  private MeterService meterService;

  public double calculateBillAmount(double unitsConsumed) {
      if (unitsConsumed < 100) {
          return 0.0;
      } else if (unitsConsumed < 200) {
          return unitsConsumed * 0.5;
      } else if (unitsConsumed < 400) {
          return 100 + (unitsConsumed * 0.65);
      } else if (unitsConsumed < 600) {
          return 200 + (unitsConsumed * 0.8);
      } else {
          return (unitsConsumed * 1.25) + 425;
      }
  }

  public Billing generateBill(Long meterId, double unitsConsumed) {
      Meter meter = meterService.getMeterById(meterId);
      User user = meter.getUser();
      
      Billing billing = new Billing();
      billing.setMeter(meter);
      billing.setUser(user);
      billing.setUnitsConsumed(unitsConsumed);
      
      double amount = calculateBillAmount(unitsConsumed);
      billing.setTotalAmount(amount);
      
      billing.setBillingStartingDate(LocalDate.now().minusDays(30));
      billing.setBillingEndingDate(LocalDate.now());
      billing.setStatus("Unpaid");
      
      return billingRepo.save(billing);
  }

  public List<Billing> getBillsByUserId(Long userId) {
      return billingRepo.findByUserId(userId);
  }

  public Billing getBillById(Long billId) {
      return billingRepo.findById(billId).orElseThrow(() -> new RuntimeException("Bill not found"));
  }

  public Billing updateBillStatus(Long billId, String status) {
      Billing bill = getBillById(billId);
      bill.setStatus(status);
      return billingRepo.save(bill);
  }
}
