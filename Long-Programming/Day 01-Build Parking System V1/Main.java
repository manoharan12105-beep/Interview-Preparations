package Day_03_24_2026_Build_Parking_System_V1;

import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    ParkingLots parkingLots = new ParkingLots();
    System.out.println("Welcome to the Parking System!");

    int currentSlot = 1;
    int totalSlots = 100;

    while(true) {
      System.out.println("1. Park a vehicle");
      System.out.println("2. Unpark a vehicle");
      System.out.println("3. Exit");
      System.out.print("Please select an option:");

      int choice = scanner.nextInt();
      scanner.nextLine();
      switch(choice) { 
        case 1: {
            System.out.print("Enter vehicle number:");
            String vehicleNumber = scanner.nextLine();
            System.out.print("Enter vehicle type:");
            String vehicleType = scanner.nextLine();
            System.out.print("Enter owner name:");
            String ownerName = scanner.nextLine();

            currentSlot = parkingLots.parkVehicle(vehicleNumber, vehicleType, ownerName, currentSlot, totalSlots);
            System.out.println();

            break;
        }
        case 2: {
            System.out.print("Enter vehicle number to unpark:");
            String vehicleNumber = scanner.nextLine();
            parkingLots.unparkVehicle(vehicleNumber, currentSlot, totalSlots);
            System.out.println();

            break;
        }

        case 3: {
            System.out.println("Thank you for using the Parking System. Goodbye!");
            return;
        }
      }
    }
  }
}
