package me.mano.HotelRoomBooking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelRoomBookingApplication implements CommandLineRunner {

	@Autowired
  private Starter start;

	public static void main(String[] args) {
		SpringApplication.run(HotelRoomBookingApplication.class, args);
	}

	@Override
	public void run(String... args) {
			start.startApp();
	}

	
}
