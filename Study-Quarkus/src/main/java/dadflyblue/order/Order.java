package dadflyblue.order;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static javax.persistence.EnumType.STRING;

@Entity(name = "orders")
public class Order extends PanacheEntity {

  public String userId;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
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

  public Order setMessage(String message) {
    this.responseMessage = message;
    return this;
  }

  @Transactional
  public static Uni<Order> saveAsync(Order order) {
    return Uni.createFrom().item(order)
            .runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
            .onItem().transform(Order::save);
  }

  @Transactional
  public static Multi<Order> getAllAsync() {
    return Multi.createFrom()
            .items(Order::getAll)
            .runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
  }

  @Transactional
  public static Stream<Order> getAll() {
    return Order.<Order>listAll().stream();
  }

  @Transactional
  public static Order save(Order order) {
    order.orderItems.forEach(i -> i.setOrder(order));
    Order.persist(order);
    return order;
  }
}
