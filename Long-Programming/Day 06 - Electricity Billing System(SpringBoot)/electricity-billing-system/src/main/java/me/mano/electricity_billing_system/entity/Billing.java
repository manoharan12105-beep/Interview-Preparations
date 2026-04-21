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
@Table(name = "billings")
public class Billing {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "meter_id", nullable = false)
  private Meter meter;

  @Column(nullable = false)
  private LocalDate billingStartingDate;

  @Column(nullable = false)
  private LocalDate billingEndingDate;
 
  private double unitsConsumed;
  private double totalAmount;

  private String status;  // Paid, Unpaid, Overdue
}
