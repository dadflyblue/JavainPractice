package dadflyblue.common;

import dadflyblue.order.OrderItem;

public class OrderItemInfo {
  public Long orderId;
  public Long productId;
  public Integer quantity;

  public static OrderItemInfo from(OrderItem s) {
    var item = new OrderItemInfo();
    {
      item.orderId = s.order.id;
      item.productId = s.productId;
      item.quantity = s.quantity;
    }
    return item;
  }

  @Override
  public String toString() {
    return "OrderItemInfo{" +
            "orderId=" + orderId +
            ", productId=" + productId +
            ", quantity=" + quantity +
            '}';
  }
}
