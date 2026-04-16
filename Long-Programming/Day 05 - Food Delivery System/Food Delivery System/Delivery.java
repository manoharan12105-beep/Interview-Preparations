public class Delivery {
  private long deliveryId;
  private long orderId;
  private long customerId;
  private String deliveryStatus;

  public Delivery(long deliveryId, long orderId, long customerId) {
    this.deliveryId = deliveryId;
    this.orderId = orderId;
    this.customerId = customerId;
    this.deliveryStatus = "Placed";
  }

  public long getOrderId() {
    return orderId;
  }

  public long getCustomerId() {
    return customerId;
  }

  public String getDeliveryStatus() {
    return deliveryStatus;
  }

  public void updateStatus(String status) {
    this.deliveryStatus = status;
  }
}