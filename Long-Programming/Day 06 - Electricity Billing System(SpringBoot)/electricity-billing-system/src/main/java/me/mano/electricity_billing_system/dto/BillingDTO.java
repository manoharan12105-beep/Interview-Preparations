package me.mano.electricity_billing_system.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BillingDTO {
  
  private Long id;
  private LocalDate billingStartingDate;
  private LocalDate billingEndingDate;
  private Double totalAmount;
  private String status;
}
