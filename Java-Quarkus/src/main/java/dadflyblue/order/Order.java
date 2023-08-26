package dadflyblue.order;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.EnumType.STRING;

@Entity(name = "orders")
public class Order extends PanacheEntity {

  public String userId;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
  public Set<OrderItem> orderItems = new HashSet<>();

  public Long total;

  public String paymentMode;

  @ManyToOne
  @JoinColumn(name = "addressId", nullable = false)
  public Address shippingAddress;

  public OffsetDateTime shippingDate;

  @Enumerated(STRING)
  public OrderStatus orderStatus;

  public String responseMessage;

  public static Order newOrder(Address address, Set<OrderItem> items, String payment, Long total) {
    var o = new Order();
    {
      o.shippingAddress = address;
      o.orderItems = Set.copyOf(items);
      o.paymentMode = payment;
      o.total = total;
    }
    return o;
  }

  public Order setStatus(OrderStatus status) {
    this.orderStatus = status;
    return this;
  }

  @Transactional
  public static Uni<Order> saveAsync(Order order) {
    return Uni.createFrom().item(order)
            .onItem().transform(Order::save);
  }

  @Transactional
  public static Uni<Order> updateAsync(Order order) {
    return Uni.createFrom().item(order)
            .onItem().transform(o -> {
              update("set " +
                      "userId=?1, total=?2, paymentMode=?3, addressId=?4, " +
                      "shippingDate=?5, orderStatus=?6, responseMessage=?7 " +
                      "where id=?8",
                      o.userId, o.total, o.paymentMode, o.shippingAddress.id,
                      o.shippingDate, o.orderStatus, o.responseMessage, o.id);
              flush();
              return o;
            });
  }

  @Transactional
  public static Multi<Order> getAllAsync() {
    return Multi.createFrom().items(
            () -> Order.<Order>listAll().stream());
  }

  @Transactional
  public static Order getById(Long id) {
    return findById(id);
  }

  @Transactional
  public static void removeById(Long id) {
    deleteById(id);
    flush();
  }

  @Transactional
  static Order save(Order order) {
    order.orderItems.forEach(i -> i.setOrder(order));
    Order.persist(order);
    Order.flush();
    return order;
  }

  @Override
  public String toString() {
    return "Order<" + id + ", " + orderStatus + ">";
  }
}
