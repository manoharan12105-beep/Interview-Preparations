package me.mano.HotelRoomBooking.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import me.mano.HotelRoomBooking.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
  List<Booking> findByUserUserId(int userId);
}
