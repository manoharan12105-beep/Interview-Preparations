package me.mano.electricity_billing_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.mano.electricity_billing_system.entity.Billing;
import me.mano.electricity_billing_system.service.BillingService;

@RestController
@RequestMapping("/billing")
public class BillingController {

  @Autowired
  private BillingService billingService;
  
  @PostMapping("/generate/{meterId}")
  public Billing generateBill(@PathVariable Long meterId, @RequestParam double units) {
    return billingService.generateBill(meterId, units);
  }

  @GetMapping("/user/{userId}")
  public List<Billing> getBillsByUserId(@PathVariable Long userId) {
    return billingService.getBillsByUserId(userId);
  }

  @GetMapping("/{id}")
  public Billing getBillById(@PathVariable Long id) {
    return billingService.getBillById(id);
  }

  @PutMapping("/{id}/status")
  public Billing updateBillStatus(@PathVariable Long id, @RequestParam String status) {
    return billingService.updateBillStatus(id, status);
  }
}

/*
  /generate/{meterId} - POST - Generate a bill for a specific meter based on units consumed
    {
    "units": 150.0
  }

  /user/{userId} - GET - Get all bills for a specific user

  /{id} - GET - Get bill details by ID

  /{id}/status - PUT - Update the status of a specific bill
    {
    "status": "Paid"
  }

*/
