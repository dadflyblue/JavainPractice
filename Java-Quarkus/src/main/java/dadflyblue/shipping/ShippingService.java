package dadflyblue.shipping;

import dadflyblue.common.OrderInfo;
import dadflyblue.order.OrderStatus;
import io.quarkus.logging.Log;
import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.vertx.core.eventbus.EventBus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.OffsetDateTime;

@ApplicationScoped
@SuppressWarnings("unused")
public class ShippingService {

  @Inject
  EventBus publisher;

  @ConsumeEvent("orders.shipping")
  void onShippingEvent(OrderInfo order) {
    Log.infov("shipping service starts to handle order event: Order<{0}, {1}>", order.id, order.orderStatus);
    handleOrder(order)
            .runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
            .subscribe().with(
                    item -> Log.infov("shipping server handled order succeed with: {0}", item),
                    t -> Log.error("shipping server handled order failed with:" + order, t)
            );
  }

  Uni<OrderInfo> handleOrder(OrderInfo order) {
    return Uni.createFrom().item(order)
            .onItem().transformToUni(o -> {
              OffsetDateTime shippingDate;
              if (OffsetDateTime.now().getHour() >= 3
                      && OffsetDateTime.now().getHour() < 20) {
                shippingDate = OffsetDateTime.now()
                        .plusDays(1);
              } else {
                return Uni.createFrom().failure(
                        new RuntimeException("The current time is off the limits to place order." + OffsetDateTime.now()));
              }
              return Uni.createFrom().item(o.setShippingDate(shippingDate));
            })
            .onItem().transformToUni(o -> Shipment.saveAsync(Shipment.fromOrder(o)).replaceWith(o))
            .onItem().invoke(o -> publishOrderEvent(o.setStatus(OrderStatus.SHIPPING_SUCCESS)))
            .onFailure().invoke(t -> publishOrderEvent(order.setStatus(OrderStatus.SHIPPING_FAILURE).setMessage(t.getMessage())));
  }

  void publishOrderEvent(OrderInfo order) {
    Log.infov("shipping service publishes order event with: Order=<{0}, {1}>", order.id, order.orderStatus);
    publisher.publish("orders", order);
  }
}
