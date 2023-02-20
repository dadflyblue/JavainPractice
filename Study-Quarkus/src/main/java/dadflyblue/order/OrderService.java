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
    publisher.publish(address, OrderInfo.from(order));
    return order;
  }

  @ConsumeEvent(value = "orders")
  @SuppressWarnings("unused")
  void onOrderReceived(OrderInfo order) {
    Log.infov("start to handle with order event: Order<{0}>", order.id);
    Uni.createFrom().item(order)
        .runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
        .onItem().transformToUni(o -> Order.findByIdAsync(o.id))
        .onItem().ifNull().fail()
        .onItem().transform(this::handleOrderStatus)
        .onItem().transformToUni(Order::updateAsync)
        .subscribe().with(
            o -> Log.infov("handled with order event succeed: {0}", o),
            t -> Log.error("handled with order event failed: " + order, t)
        );
  }

  Order handleOrderStatus(Order order) {
    switch (order.orderStatus) {
      case INITIATION_SUCCESS:
        order.orderStatus = OrderStatus.RESERVE_INVENTORY;
        publishOrderEvent(order, "orders.produce");
        break;
      case INVENTORY_SUCCESS:
        order.orderStatus = OrderStatus.PREPARE_SHIPPING;
        publishOrderEvent(order, "orders.shipping");
        break;
      case SHIPPING_FAILURE:
        order.orderStatus = OrderStatus.REVERT_INVENTORY;
        publishOrderEvent(order, "orders.produce");
        break;
    }
    return order;
  }

}
