package me.mano.electricity_billing_system.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.mano.electricity_billing_system.entity.Billing;
import me.mano.electricity_billing_system.entity.Payment;
import me.mano.electricity_billing_system.repo.PaymentRepo;

@Service
public class PaymentService {
  
  @Autowired
  private PaymentRepo paymentRepo;

  @Autowired
  private BillingService billingService;

  public Payment processPayment(Payment payment, Long billId) {
    Billing bill = billingService.getBillById(billId);
    
    // Simple validation
    if(payment.getAmountPaid() >= bill.getTotalAmount()) {
        payment.setStatus("Success");
        billingService.updateBillStatus(billId, "Paid");
    } else {
        payment.setStatus("Pending");
    }
    
    payment.setBill(bill);
    if(payment.getPaymentDate() == null) {
        payment.setPaymentDate(LocalDate.now());
    }
    
    return paymentRepo.save(payment);
  }

  public List<Payment> getPaymentsByBillId(Long billId) {
    return paymentRepo.findByBillId(billId);
  }
}
