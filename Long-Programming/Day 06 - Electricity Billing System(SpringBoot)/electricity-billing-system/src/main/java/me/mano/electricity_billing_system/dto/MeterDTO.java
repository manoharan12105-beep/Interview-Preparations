package me.mano.electricity_billing_system.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeterDTO {

  private Long id;
  private String meterNumber;
  private LocalDate installationDate;
  private String status;
}
