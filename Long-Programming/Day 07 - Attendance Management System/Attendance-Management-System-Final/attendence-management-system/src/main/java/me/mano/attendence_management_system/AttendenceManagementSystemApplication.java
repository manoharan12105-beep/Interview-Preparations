package me.mano.attendence_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AttendenceManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendenceManagementSystemApplication.class, args);
	}

}
