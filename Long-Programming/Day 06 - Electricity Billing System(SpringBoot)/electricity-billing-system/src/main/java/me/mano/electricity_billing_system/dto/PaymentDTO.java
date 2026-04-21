package me.mano.electricity_billing_system.dto;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentDTO {
  
  private Long id;
  private LocalDate paymentDate;
  private Double amount;
  private String paymentMethod;
  private String status;
}
