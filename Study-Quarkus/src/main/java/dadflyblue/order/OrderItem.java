package dadflyblue.order;

import com.fasterxml.jackson.annotation.*;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "order_items")
public class OrderItem extends PanacheEntity {
  @ManyToOne
  @JoinColumn(name="orderId", nullable = false)
  @JsonIgnoreProperties({"orderItems"})
  public Order order;
  public long productId;
  public int quantity;

  public OrderItem setOrder(Order order) {
    this.order = order;
    return this;
  }

  public static OrderItem of(long productId, int quantity) {
    OrderItem item = new OrderItem();
    {
      item.productId = productId;
      item.quantity = quantity;
    }
    return item;
  }
}