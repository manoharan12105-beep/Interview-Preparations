import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String adminId = "ADMIN";
    String adminPass = "Admin123_";
    long userId = 1;
    long restId = 1;

    List<Restaurant> restList = new ArrayList<>();
    List<Customer> userList = new ArrayList<>();

    // Main Menu
    while (true) {
      System.out.println("\n====MENU====");
      System.out.println("1) Register");
      System.out.println("2) Login");
      System.out.println("3) Exit");
      System.out.print("Enter a choice : ");

      switch (Integer.parseInt(scanner.nextLine())) {

        case 1: {
          Customer user = new Customer();
          user.addUser(userId);
          userList.add(user);

          System.out.println("User Registered Successfully with User ID : " + userId);
          System.out.println();
          userId++;
          break;
        }

        case 2: {
          System.out.println("\n1) Admin Login");
          System.out.println("2) Manager Login");
          System.out.println("3) Customer Login");
          System.out.println("4) Back to Main Menu");
          System.out.print("Enter a choice : ");

          switch (Integer.parseInt(scanner.nextLine())) {

            // Admin Login
            case 1: {
              System.out.print("Enter Admin ID : ");
              String id = scanner.nextLine();

              System.out.print("Enter Admin Password : ");
              String pass = scanner.nextLine();

              if (adminId.equals(id) && adminPass.equals(pass)) {
                System.out.println("Admin Login Successful\n");

                while (true) {
                  System.out.println("\n====Admin Menu====");
                  System.out.println("1) Add Restaurant");
                  System.out.println("2) View Restaurants");
                  System.out.println("3) Logout");
                  System.out.print("Enter a choice : ");

                  String choice = scanner.nextLine();

                  switch (Integer.parseInt(choice)) {

                    case 1: {
                      Restaurant rest = new Restaurant();
                      rest.addRestaurant(restId);
                      restList.add(rest);

                      System.out.println("Restaurant Added Successfully with Restaurant ID : " + restId);
                      System.out.println();
                      restId++;
                      break;
                    }

                    case 2: {
                      if (restList.size() == 0) {
                        System.out.println("No Restaurants Available");
                      } else {
                        for (Restaurant r : restList) {
                          r.viewRestaurant();
                        }
                      }
                      System.out.println();
                      break;
                    }

                    case 3: {
                      System.out.println("Admin Logged Out Successfully\n");
                      break;
                    }

                    default: {
                      System.out.println("Invalid Choice\n");
                    }
                  }

                  if (Integer.parseInt(choice) == 3) {
                    break;
                  }
                }

              } else {
                System.out.println("Invalid Admin Credentials\n");
              }
              break;
            }

            // Manager Login
            case 2 : {
              System.out.println("Enter Manager ID : ");
              String id = scanner.nextLine();
              System.out.println("Enter Manager Password : ");
              String pass = scanner.nextLine();
              boolean found = false;
              for (Restaurant r : restList) {
                if (r.getManagerId().equals(id) && r.getPassword().equals(pass)) {
                  System.out.println("Manager Login Successful\n");
                  found = true;

                  while(true) {
                    System.out.println("\n====Manager Menu====");
                    System.out.println("1) Add Food Item");
                    System.out.println("2) Remove Food Item");
                    System.out.println("3) View Food Items");
                    System.out.println("4) Update Delivery Status");
                    System.out.println("5) Logout");
                    System.out.print("Enter a choice : ");
                    String choice = scanner.nextLine();

                    switch(Integer.parseInt(choice)) {
                      case 1 : {
                        r.addFoodItem();
                        System.out.println();
                        break;
                      }
                      case 2 : {
                        r.removeFoodItem();
                        System.out.println();
                        break;
                      }
                      case 3 : {
                        r.viewFoodItems();
                        break;
                      }
                      case 4 : {
                        r.updateDeliveryStatus();
                        System.out.println();
                        break;
                      }
                      case 5 : {
                        System.out.println("Manager Logged Out Successfully\n");
                        break;
                      }
                    }
                    if(choice.equals("5")) {
                      break;
                    }

                  }
                  break;
                }
              }
              if (!found) {
                System.out.println("Invalid Manager Credentials\n");
              }
              break;
            }

            // Customer Login
            case 3 : {
              System.out.println("Enter User ID : ");
              String id = scanner.nextLine();
              System.out.println("Enter User Password : ");
              String pass = scanner.nextLine();
              boolean found = false;
              for (Customer c : userList) {
                if (c.getUserId() == Long.parseLong(id) && c.getPassword().equals(pass)) {
                  System.out.println("Customer Login Successful\n");
                  found = true;

                  while(true) {
                    System.out.println("\n====Customer Menu====");
                    System.out.println("1) View Restaurants");
                    System.out.println("2) Choose a Restaurant");
                    System.out.println("3) Logout");
                    System.out.print("Enter a choice : ");
                    String choice = scanner.nextLine();

                    switch(Integer.parseInt(choice)) {
                      case 1 : {
                        if (restList.size() == 0) {
                          System.out.println("No Restaurants Available");
                        } else {
                          for (Restaurant r : restList) {
                            r.viewRestaurant();
                          }
                        }
                        break;
                      }
                      case 2 : {
                        System.out.println("Enter Restaurant ID : ");
                        String restaurantId = scanner.nextLine();
                        boolean restFound = false;
                        for (Restaurant r : restList) {
                          if (r.getRestId() == Long.parseLong(restaurantId)) {
                            restFound = true;
                            System.out.println("Restaurant Selected Successfully\n");
                            r.viewFoodItems();

                            while(true) {
                              System.out.println("\n====Order Menu====");
                              System.out.println("1) Place order");
                              System.out.println("2) Delivery Status");
                              System.out.println("3) Back to Customer Menu");
                              System.out.print("Enter a choice : ");
                              String orderChoice = scanner.nextLine();
                              switch(Integer.parseInt(orderChoice)) {
                                case 1 : {
                                  r.placeOrder(c);
                                  break;
                                }

                                case 2 : {
                                  r.viewOrderStatus(c);
                                  break;
                                }

                                case 3 : {
                                  System.out.println("Returning to Customer Menu...\n");
                                  break;
                                }
                              }
                              if(orderChoice.equals("2")) {
                                break;
                              } 
                            }
                            break;
                          }
                        }
                        if (!restFound) {
                          System.out.println("Invalid Restaurant ID\n");
                        }
                        break;
                      }
                      case 3 : {
                        System.out.println("Customer Logged Out Successfully\n");
                        break;
                      }
                    }
                    if(choice.equals("3")) {
                      break;
                    }
                  }
                  break;
                }
              }
              if (!found) {
                System.out.println("Invalid Customer Credentials\n");
              }
              break;
            }

            default: {
              System.out.println("Invalid Choice\n");
            }
          }
          break;
        }

        case 3: {
          System.out.println("Exiting...");
          return;
        }

        default: {
          System.out.println("Invalid Choice\n");
        }
      }
    }
  }
}