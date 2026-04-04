package me.mano.HotelRoomBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import me.mano.HotelRoomBooking.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  User findByEmail(String email);
  
}
