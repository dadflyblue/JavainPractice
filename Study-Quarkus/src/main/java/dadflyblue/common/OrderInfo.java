package dadflyblue.common;

import dadflyblue.order.Order;
import dadflyblue.order.OrderStatus;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderInfo {

  public Long id;

  public String userId;

  public Set<OrderItemInfo> orderItems;

  public Long total;

  public String paymentMode;

  public AddressInfo shippingAddress;

  public OffsetDateTime shippingDate;

  public OrderStatus orderStatus;

  public String responseMessage;

  public OrderInfo setStatus(OrderStatus status) {
    this.orderStatus = status;
    return this;
  }

  public OrderInfo setMessage(String message) {
    this.responseMessage = message;
    return this;
  }

  public static OrderInfo from(Order s) {
    var order = new OrderInfo();
    {
      order.id = s.id;
      order.orderStatus = s.orderStatus;
      order.orderItems = s.orderItems.stream()
              .map(OrderItemInfo::from).collect(Collectors.toSet());
      order.paymentMode = s.paymentMode;
      order.responseMessage = s.responseMessage;
      order.shippingAddress = AddressInfo.from(s.shippingAddress);
      order.userId = s.userId;
      order.total = s.total;
      order.shippingDate = s.shippingDate;
    }
    return order;
  }

  @Override
  public String toString() {
    return "OrderInfo{" +
            "id=" + id +
            ", userId='" + userId + '\'' +
            ", orderItems=" + orderItems +
            ", total=" + total +
            ", paymentMode='" + paymentMode + '\'' +
            ", shippingAddress=" + shippingAddress +
            ", shippingDate=" + shippingDate +
            ", orderStatus=" + orderStatus +
            ", responseMessage='" + responseMessage + '\'' +
            '}';
  }
}
