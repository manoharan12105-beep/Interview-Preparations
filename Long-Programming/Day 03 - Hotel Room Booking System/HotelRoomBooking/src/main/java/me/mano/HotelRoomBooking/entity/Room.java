package me.mano.HotelRoomBooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Room {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer roomId;

  @Column(nullable = false)
  private String roomType;

  @Column(nullable = false)
  private double price;

  @Column(nullable = false)
  private int capacity;

  @Column(nullable = false)
  private boolean isAvailable;
}
