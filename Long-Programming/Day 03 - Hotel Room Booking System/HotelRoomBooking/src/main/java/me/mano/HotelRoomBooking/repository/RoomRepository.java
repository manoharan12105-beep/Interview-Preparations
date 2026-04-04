package me.mano.HotelRoomBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import me.mano.HotelRoomBooking.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
  
}
