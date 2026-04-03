import java.util.*;
public class Item{
    private int itemId;
    private float price;
    private String itemName;

    Item(int itemId,float price,String idemName) {
        this.itemId=itemId;
        this.price=price;
        this.itemName=idemName;
    }

  
    String getName(int id,Set<Item> items) {
        String str="";
        for(Item product:items){
            if(product.getItemId()==id){
                str=product.getItemName();
            }
        }
        return str;
    }
    float getPriceforEach(int id,Set<Item> items){
        float n=0;
        for(Item product:items){
            if(product.getItemId()==id){
                n=product.getPrice();
            }
        }
        return n;
    }
    public int getItemId() {
        return itemId;
    }
    public float getPrice() {
        return price;
    }
    public String getItemName() {
        return itemName;
    }

}
