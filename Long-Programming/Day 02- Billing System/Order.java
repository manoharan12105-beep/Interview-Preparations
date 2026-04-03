public class Order {
    private int quantity;
    private int item_id;
  
    Order(int qn,int itemId) {
        this.quantity=qn;
        this.item_id=itemId;
    }
  
    public int getQuantity() {
        return quantity;
    }
  
    public int getItem_id() {
        return item_id;
    }

}
