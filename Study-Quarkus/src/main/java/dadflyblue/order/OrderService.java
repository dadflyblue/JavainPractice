package dadflyblue.order;

import io.quarkus.logging.Log;
import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.vertx.core.eventbus.EventBus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class OrderService {
  @Inject
  EventBus publisher;

  @Transactional
  public Uni<Order> createOrder(Order order) {
    return Order.saveAsync(order.setStatus(OrderStatus.INITIATION_SUCCESS))
            .onItem().transform(this::publishStatusChanged);
  }

  Order publishStatusChanged(Order order) {
    publisher.publish("orders", order);
    return order;
  }

  @ConsumeEvent(value = "orders")
  void onOrderReceived(Order o) {
    var order = Order.<Order>findById(o.id);
    if (order == null) {
      Log.warnv("Receive an order without a valid id - {0}", o);
      return;
    }

    switch (o.orderStatus) {
      case INITIATION_SUCCESS:
        order.orderStatus = OrderStatus.RESERVE_INVENTORY;
        break;
      case INVENTORY_SUCCESS:
        order.orderStatus = OrderStatus.PREPARE_SHIPPING;
        break;
      case SHIPPING_FAILURE:
        order.orderStatus = OrderStatus.REVERT_INVENTORY;
        break;
    }

    Order.save(order.setMessage(o.responseMessage));
    publisher.publish("orders", order);
  }

}
