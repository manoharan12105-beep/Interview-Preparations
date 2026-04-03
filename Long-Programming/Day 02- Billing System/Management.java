import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Management {
    public static void main(String[] args) {
        Item item =new Item();
        
         Set<Item> itemList =new HashSet<>();
      Item ob1=new Item(101,10,"chocolate");
      itemList.add(ob1);
  
      Item ob2=new Item(102,20,"biscuit");
      itemList.add(ob2);
  
      Item ob3=new Item(103,50,"bear toy");
      itemList.add(ob3);
  
      Item ob4=new Item(104,100,"wall clock");
      itemList.add(ob4);
  
      Item ob5=new Item(105,80,"story book");
      itemList.add(ob5);
      Scanner scanner = new Scanner(System.in);
      System.out.println("Welcome to our store");
      System.out.println("Enter number of product you have taken:");
      int n=scanner.nextInt();
      Order[] orderedelements = new Order[n];
       for(int i=0;i<n;i++){
      System.out.println("give the product id and its Quantity one by one");
      int id=scanner.nextInt();
      int qn=scanner.nextInt();
     Order order = new Order(qn,id);
     orderedelements[i]=order;
  
      }
      System.out.println("ID     Name        Quantity      Price");
      System.out.println(".......................................");
      float sum=0;
      for(Order order:orderedelements){
          
       String name=item.getName(order.getItem_id(),itemList);
       float price=item.getPriceforEach(order.getItem_id(),itemList);
       price=price*order.getQuantity();
       sum+=price;
        System.out.printf("%-5d %-10s %-5d %-5.2f",order.getItem_id(),name,order.getQuantity(),price);
        System.out.println();
      }
      System.out.println("Total              :"+sum);
}
}
