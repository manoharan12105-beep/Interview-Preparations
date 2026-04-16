import java.util.*;
public class Customer {
  private long userId;
  private String name;
  private String password;
  private  String address;
  private String phoneNumber;
  static Scanner scanner = new Scanner(System.in);

  public void addUser(long userId) {
    this.userId = userId;
    System.out.print("Enter Name : ");
    this.name = scanner.nextLine();
    System.out.print("Enter Password : ");
    this.password = scanner.nextLine();
    System.out.print("Enter address : ");
    this.address = scanner.nextLine();
    System.out.print("Enter Phone no : ");
    this.phoneNumber = scanner.nextLine();
  }

  public long getUserId() {
    return userId;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public String getAddress() {
    return address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public static Scanner getScanner() {
    return scanner;
  }
}