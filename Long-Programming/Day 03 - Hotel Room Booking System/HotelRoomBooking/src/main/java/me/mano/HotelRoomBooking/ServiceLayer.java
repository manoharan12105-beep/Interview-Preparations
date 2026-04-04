package me.mano.HotelRoomBooking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.mano.HotelRoomBooking.entity.Booking;
import me.mano.HotelRoomBooking.entity.Room;
import me.mano.HotelRoomBooking.entity.User;
import me.mano.HotelRoomBooking.repository.BookingRepository;
import me.mano.HotelRoomBooking.repository.RoomRepository;
import me.mano.HotelRoomBooking.repository.UserRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class ServiceLayer {
  @Autowired
  private BookingRepository bookingRepo;

  @Autowired
  private RoomRepository roomRepo;

  @Autowired
  private UserRepository userRepo;

  Scanner scanner = new Scanner(System.in);

  /**
   * Create new User record in User Table
   * @param username
   * @param email
   * @param pass
   */
  public void createUser(String username, String email, String pass) {
    User user = new User();
    user.setName(username);
    user.setEmail(email);
    user.setPassword(pass);
    user.setRoll("User");

    userRepo.save(user);
  }


  /**
   * Login Validation Method
   * @param email
   * @param password
   * @return
   */
  public User validate(String email, String password) {
    User user = userRepo.findByEmail(email);

    if(user == null) {
      System.out.println("Email not Found, Retry...");
      return null;
    }

    if(!user.getPassword().equals(password)) {
      System.out.println("Wrong Password, Retry");
      return null;
    }

    System.out.println("Login Successful");
    return user;

  }


  // Shows all Available Room Information
  public void viewAvailableRooms() {
    List<Room> rooms = roomRepo.findAll();

    System.out.println("\nAvailable Rooms:");
    System.out.println("ID | Type | Price");

    for (Room r : rooms) {
        if (r.isAvailable()) {
          System.out.println(r.getRoomId() + " | " + r.getRoomType() + " | " + r.getPrice());
        }
    }
  }


  /**
   * Room Booking
   * @param userId
   * @param roomId
   * @param checkIn
   * @param checkOut
   */
  public void bookRoom(int userId, int roomId, LocalDate checkIn, LocalDate checkOut) {
    User user = userRepo.findById(userId).orElse(null);
    if (user == null) {
      System.out.println("User not found");
      return;
    }

    Room room = roomRepo.findById(roomId).orElse(null);
    if (room == null) {
      System.out.println("Room not found");
      return;
    }

    long days = ChronoUnit.DAYS.between(checkIn, checkOut);
    double totalPrice = days * room.getPrice();

    Booking booking = new Booking();
    booking.setUser(user);
    booking.setRoom(room);
    booking.setCheckIn(checkIn);
    booking.setCheckOut(checkOut);
    booking.setTotalPrice(totalPrice);
    booking.setStatus("BOOKED");

    bookingRepo.save(booking);

    System.out.println("Booking successful!");
  }


  /**
   * Show all Rooms booked by a User
   * @param userId
   */
  public void getUserBookings(int userId) {
    List<Booking> bookings = bookingRepo.findByUserUserId(userId);

    if (bookings.isEmpty()) {
      System.out.println("No bookings found");
      return;
    }

    for (Booking b : bookings) {
      System.out.println("==== Your Bookings ====");
      System.out.println("Booking ID: " + b.getBookingId());
      System.out.println("Room ID: " + b.getRoom().getRoomId());
      System.out.println("Check-in: " + b.getCheckIn());
      System.out.println("Check-out: " + b.getCheckOut());
      System.out.println("Total Price: " + b.getTotalPrice());
      System.out.println("Status: " + b.getStatus());
    }
  }


  /**
   * Booking Cancellation
   * @param bookingId
   */
  public void cancelBooking(int bookingId) {
    Booking booking = bookingRepo.findById(bookingId).orElse(null);

    if (booking == null) {
      System.out.println("Booking not found");
      return;
    }

    booking.setStatus("CANCELLED");
    bookingRepo.save(booking);

    System.out.println("Booking cancelled successfully!");
  }


  /**
   * Admin method - Adding new Rooms
   * @param type
   * @param price
   */
  public void addRoom(String type, double price) {

    Room room = new Room();
    room.setRoomType(type);
    room.setPrice(price);
    room.setAvailable(true);

    roomRepo.save(room);

    System.out.println("Room added successfully!");
  }


  /**
   * Admin method - View all Rooms
   */
  public void viewAllRooms() {
    List<Room> rooms = roomRepo.findAll();

    for (Room r : rooms) {
      System.out.println(r.getRoomId() + " | " + r.getRoomType() + " | " + r.getPrice() + " | " +  r.isAvailable());
    }
 }


 /**
  * Admin method - Remove Existing Room
  * @param roomId
  */
 public void deleteRoom(int roomId) {
    if (!roomRepo.existsById(roomId)) {
      System.out.println("Room not found");
      return;
    }

    roomRepo.deleteById(roomId);

    System.out.println("Room deleted successfully!");
 }


 /**
  * Admin method - View all Bookings
  */
 public void viewAllBookings() {
    List<Booking> list = bookingRepo.findAll();

    for (Booking b : list) {
        System.out.println("==== Current Bookings ====");
        System.out.println("Booking ID: " + b.getBookingId());
        System.out.println("User: " + b.getUser().getName());
        System.out.println("Room: " + b.getRoom().getRoomId());
        System.out.println("From: " + b.getCheckIn());
        System.out.println("To: " + b.getCheckOut());
        System.out.println("Status: " + b.getStatus());
    }
  }
}
