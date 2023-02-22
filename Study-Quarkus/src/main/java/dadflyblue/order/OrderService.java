package dadflyblue.order;

import dadflyblue.common.OrderInfo;
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
            .onItem().transform(o -> publishOrderEvent(order, "orders"));
  }

  Order publishOrderEvent(Order order, String address) {
    Log.infov("order service publishes order event: {0} to {1}", order, address);
    publisher.publish(address, OrderInfo.from(order));
    return order;
  }

  @ConsumeEvent(value = "orders")
  @SuppressWarnings("unused")
  void onOrderReceived(OrderInfo order) {
    Log.infov("order service starts to handle order event: Order<{0}, {1}>", order.id, order.orderStatus);
    Uni.createFrom().item(() -> Order.<Order>findById(order.id))
        .runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
        .onItem().ifNull().fail() // raises NoSuchElementException if failed
        .onItem().transform(o -> mergeProperties(order, o))
        .onItem().transform(this::handleOrderStatus)
        .onItem().transformToUni(Order::updateAsync)
        .subscribe().with(
            o -> Log.infov("order service handled order event succeed: {0}", o),
            t -> Log.error("order service handled order event failed: " + order, t)
        );
  }

  Order mergeProperties(OrderInfo newer, Order order) {
    order.orderStatus = newer.orderStatus;
    order.responseMessage = newer.responseMessage;
    order.shippingDate = newer.shippingDate;
    return order;
  }

  Order handleOrderStatus(Order order) {
    switch (order.orderStatus) {
      case INITIATION_SUCCESS:
        order.orderStatus = OrderStatus.RESERVE_INVENTORY;
        publishOrderEvent(order, "orders.product");
        break;
      case INVENTORY_SUCCESS:
        order.orderStatus = OrderStatus.PREPARE_SHIPPING;
        publishOrderEvent(order, "orders.shipping");
        break;
      case SHIPPING_FAILURE:
        order.orderStatus = OrderStatus.REVERT_INVENTORY;
        publishOrderEvent(order, "orders.product");
        break;
    }
    return order;
  }

}
