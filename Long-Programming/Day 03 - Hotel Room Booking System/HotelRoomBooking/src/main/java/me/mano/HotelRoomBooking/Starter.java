package me.mano.HotelRoomBooking;

import java.time.LocalDate;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import me.mano.HotelRoomBooking.entity.User;



@Component
public class Starter {

    @Autowired
    private ServiceLayer service;

    private User currentUser;

    public void startApp() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Hotel Room Booking");

        while(true) {
          System.out.println("==== Main Menu ====");
          System.out.println("1) Register.");
          System.out.println("2) Login.");
          System.out.println("3) Exit.");

          int choice = scanner.nextInt();
          scanner.nextLine();
          System.out.println();

          switch (choice) {

            //Create User
            case 1 : {
              System.out.print("Enter Username : ");
              String username = scanner.nextLine();

              System.out.print("Enter Mail Id : ");
              String email = scanner.nextLine();

              System.out.print("Enter Password : ");
              String pass = scanner.nextLine();

              service.createUser(username, email, pass);

              break;
            } 
            
            //Login
            case 2 : {
              System.out.print("Enter Mail Id : ");
              String email = scanner.nextLine();

              System.out.print("Enter Password : ");
              String pass = scanner.nextLine();

              currentUser = service.validate(email, pass) ;
              boolean flag = true;
              //User Menu
              if(currentUser.getRoll().equals("User")) {
                while(true && flag) {
                  System.out.println("\n==== USER MENU ====");
                  System.out.println("1. View Available Rooms");
                  System.out.println("2. Book Room");
                  System.out.println("3. View My Bookings");
                  System.out.println("4. Cancel Booking");
                  System.out.println("5. Logout");
                  int ch = scanner.nextInt();
                  scanner.nextLine();

                  switch(ch) {
                    case 1 : {
                      service.viewAvailableRooms();
                      break;
                    }

                    case 2: {
                      System.out.print("Enter Room ID: ");
                      int roomId = scanner.nextInt();
                      scanner.nextLine();

                      System.out.print("Enter Check-in (yyyy-mm-dd): ");
                      LocalDate checkIn = LocalDate.parse(scanner.next());

                      System.out.print("Enter Check-out (yyyy-mm-dd): ");
                      LocalDate checkOut = LocalDate.parse(scanner.next());

                      service.bookRoom(currentUser.getUserId(), roomId, checkIn, checkOut);
                      break;
                    }

                    case 3 : {
                      service.getUserBookings(currentUser.getUserId());
                      break;
                    } 
                            
                    case 4 : {
                      System.out.print("Enter Booking ID: ");
                      int bookingId = scanner.nextInt();
                      scanner.nextLine();

                      service.cancelBooking(bookingId);
                      break;
                    }
                            
                    case 5 : {
                        System.out.println("Logged out!");
                        flag = false;
                        break;
                    }

                    default:
                        System.out.println("Invalid choice!");

                  }
                }
              }
              

              //Admin Menu
              else if(currentUser.getRoll().equals("Admin"))
                while(true && flag) {
                  System.out.println("\n==== ADMIN MENU ====");
                  System.out.println("1. Add Room");
                  System.out.println("2. View All Rooms");
                  System.out.println("3. Delete Room");
                  System.out.println("4. View All Bookings");
                  System.out.println("5. Logout");

                  int ch = scanner.nextInt();
                  scanner.nextLine();

                  switch (ch) {

                    case 1: {
                      System.out.print("Enter Room Type: ");
                      String type = scanner.nextLine();

                      System.out.print("Enter Price: ");
                      double price = scanner.nextDouble();

                      service.addRoom(type, price);
                      break;
                    }

                    case 2: {
                      service.viewAllRooms();
                      break;
                    }

                    case 3: {
                      System.out.print("Enter Room ID to delete: ");
                      int roomId = scanner.nextInt();

                      service.deleteRoom(roomId);
                      break;
                    }

                    case 4: {
                      service.viewAllBookings();
                      break;
                    }

                    case 5: {
                      System.out.println("Admin logged out!");
                      flag = false;
                      break;
                    }

                    default:
                      System.out.println("Invalid choice!");
                  }
                }
              
                break;
              }

            //Exit
            case 3 : {
              System.out.println("Thank you!!");
              return;
            }

            default : {
              System.out.println("Invalid Input : ");
            }
            System.out.println();

          }
        }
    }
}
