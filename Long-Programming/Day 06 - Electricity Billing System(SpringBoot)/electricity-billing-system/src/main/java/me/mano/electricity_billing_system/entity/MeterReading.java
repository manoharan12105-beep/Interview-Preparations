package me.mano.electricity_billing_system.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "meter_readings")
public class MeterReading {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private double readingValue;

  @Column(nullable = false)
  private LocalDate readingDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "meter_id", nullable = false)
  private Meter meter;
}
