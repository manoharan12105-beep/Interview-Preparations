import java.util.*;
public class Restaurant {
  private long restId;
  private String restName;
  private String managerId;
  private String password;
  private long foodId = 1;
  private String restRating = "0.0";
  private List<List<String>> foodList = new ArrayList <> (); // id, name, price, category
  private List<List<String>> orderList = new ArrayList <> (); // orderId, foodId, customerId, amount, price, status
  private List<Delivery> deliveryList = new ArrayList<>();
  Scanner scanner = new Scanner(System.in);

  /**
   * Adds a restaurant to the system.
   * @param id
   */
  public void addRestaurant(long id) {
    this.restId = id;
    System.out.println("Enter Restaurant name : ");
    this.restName = scanner.nextLine();
    System.out.println("Enter Manager Id : ");
    this.managerId = scanner.nextLine();
    System.out.println("Enter Password : ");
    this.password = scanner.nextLine();
  }

  // Getters

  public long getRestId() {
    return restId;
  }

  public String getRestName() {
    return restName;
  }

  public String getManagerId() {
    return managerId;
  }

  public String getPassword() {
    return password;
  }

  public long getFoodId() {
    return foodId;
  }

  public String getRestRating() {
    return restRating;
  }

  public List<List<String>> getFoodList() {
    return foodList;
  }

  /**
   * Displays the details of the restaurant.
   */
  public void viewRestaurant() {
    System.out.println("Restaurant ID : " + this.restId);
    System.out.println("Restaurant Name : " + this.restName);
    System.out.println("Manager ID : " + this.managerId);
    System.out.println();
  }

  /**
   * Adds a food item to the restaurant's menu.
   */
  public void addFoodItem() {
    List<String> foodItem = new ArrayList<>();
    foodItem.add(String.valueOf(foodId));
    System.out.println("Enter Food Name : ");
    foodItem.add(scanner.nextLine());
    System.out.println("Enter Food Price : ");
    foodItem.add(scanner.nextLine());
    System.out.println("Enter Food Category (Veg / Non-Veg / Juice / Dessert): ");
    foodItem.add(scanner.nextLine());
    foodList.add(foodItem);
    System.out.println("Food Item Added Successfully with Food ID : " + foodId);
    foodId++;
  }

  /**
   * Displays the food items in the restaurant's menu.
   */
  public void viewFoodItems() {
    if (foodList.isEmpty()) {
      System.out.println("No Food Items Available");
    } else {
      for (List<String> foodItem : foodList) {
        System.out.println("Food ID : " + foodItem.get(0));
        System.out.println("Food Name : " + foodItem.get(1));
        System.out.println("Food Price : " + foodItem.get(2));
        System.out.println("Food Category : " + foodItem.get(3));
        System.out.println();
      }
    }
  }

  /**
   * Removes a food item from the restaurant's menu.
   */
  public void removeFoodItem() {
    System.out.println("Enter Food ID to Remove : ");
    String id = scanner.nextLine();
    boolean found = false;
    for (List<String> foodItem : foodList) {
      if (foodItem.get(0).equals(id)) {
        foodList.remove(foodItem);
        System.out.println("Food Item Removed Successfully");
        found = true;
        break;
      }
    }
    if (!found) {
      System.out.println("Food Item Not Found");
    }
  }

  /**
   * Places an order for a food item by a customer.
   * @param c
   */
  public void placeOrder(Customer c) {
    if (foodList.isEmpty()) {
      System.out.println("No Food Items Available");
    } else {
      System.out.println("Food Items Available : ");
      for (List<String> foodItem : foodList) {
        System.out.println("Food ID : " + foodItem.get(0));
        System.out.println("Food Name : " + foodItem.get(1));
        System.out.println("Food Price : " + foodItem.get(2));
        System.out.println("Food Category : " + foodItem.get(3));
        System.out.println();
      }
      System.out.println("Enter Food ID to Order : ");
      String id = scanner.nextLine();
      boolean found = false;
      for (List<String> foodItem : foodList) {
        if (foodItem.get(0).equals(id)) {
          List<String> order = new ArrayList<>();

          order.add(String.valueOf(orderList.size() + 1)); // orderId
          order.add(foodItem.get(0)); // foodId
          order.add(String.valueOf(c.getUserId())); // customerId
          System.out.println("Enter Amount : ");
          order.add(scanner.nextLine()); // amount
          order.add(foodItem.get(2)); // price
          order.add("Placed"); // status

          orderList.add(order);

          long deliveryId = deliveryList.size() + 1;
          Delivery d = new Delivery(deliveryId, Long.parseLong(order.get(0)), c.getUserId());
          deliveryList.add(d);

          System.out.println("Order Placed Successfully with Order ID : " + order.get(0));
          System.out.println();

          found = true;
          break;
        }
      }
      if (!found) {
        System.out.println("Food Item Not Found");
      }
    }
  }

  /**
   * Displays the order status of a customer.
   * @param c
   */
  public void viewOrderStatus(Customer c) {
    boolean found = false;
    for (List<String> order : orderList) {
      if (order.get(2).equals(String.valueOf(c.getUserId()))) {

        System.out.println("Order ID : " + order.get(0));
        System.out.println("Food ID : " + order.get(1));
        System.out.println("Amount : " + order.get(3));
        System.out.println("Price : " + order.get(4));

        for (Delivery d : deliveryList) {
          if (d.getOrderId() == Long.parseLong(order.get(0))) {
            System.out.println("Status : " + d.getDeliveryStatus());
          }
        }

        System.out.println();
        found = true;
      }
    }
    if (!found) {
      System.out.println("No Orders Found");
    }
  }


  /**
   * Updates the delivery status of an order.
   */
  public void updateDeliveryStatus() {
    System.out.println("Enter Order ID : ");
    String id = scanner.nextLine();

    for (Delivery d : deliveryList) {
      if (d.getOrderId() == Long.parseLong(id)) {
        System.out.println("Enter new status (Preparing / Out / Delivered) : ");
        d.updateStatus(scanner.nextLine());
        System.out.println("Status Updated Successfully");
        return;
      }
    }

    System.out.println("Order not found");
  }
}