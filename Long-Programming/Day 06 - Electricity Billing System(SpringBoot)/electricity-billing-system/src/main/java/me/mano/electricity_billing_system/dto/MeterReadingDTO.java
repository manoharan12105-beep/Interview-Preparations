package me.mano.electricity_billing_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeterReadingDTO {
  
  private Long id;
  private Double readingValue;
  private String readingDate;
}
