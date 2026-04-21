package me.mano.electricity_billing_system.entity;

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

import java.time.LocalDate;

@Data
@Entity
@Table(name = "meters")
public class Meter {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String meterNumber;

  @Column(nullable = false)
  private LocalDate installationDate;

  @Column(nullable = false)
  private String status;   // Active, Inactive, Maintenance

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn (name = "user_id", nullable = false)
  private User user;
  
}
