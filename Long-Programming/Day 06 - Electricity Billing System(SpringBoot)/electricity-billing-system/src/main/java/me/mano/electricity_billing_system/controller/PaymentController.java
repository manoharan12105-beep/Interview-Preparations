package me.mano.electricity_billing_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.mano.electricity_billing_system.entity.Payment;
import me.mano.electricity_billing_system.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
  
  @Autowired
  private PaymentService paymentService;

  @PostMapping("/bill/{billId}")
  public Payment processPayment(@RequestBody Payment payment, @PathVariable Long billId) {
    return paymentService.processPayment(payment, billId);
  }

  @GetMapping("/bill/{billId}")
  public List<Payment> getPaymentsByBillId(@PathVariable Long billId) {
    return paymentService.getPaymentsByBillId(billId);
  }
}

/*
  /bill/{billId} - POST - Process a payment for a specific bill
    {
    "paymentDate": "2026-05-01",
    "amount": 150.00,
    "paymentMethod": "Credit Card",
    "transactionId": "TXN123456789"
  }

  /bill/{billId} - GET - Get all payments for a specific bill

*/
