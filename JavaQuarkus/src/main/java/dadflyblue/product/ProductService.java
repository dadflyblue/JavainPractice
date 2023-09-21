package dadflyblue.product;

import dadflyblue.common.OrderInfo;
import dadflyblue.common.OrderItemInfo;
import dadflyblue.order.OrderStatus;
import io.quarkus.logging.Log;
import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.smallrye.mutiny.tuples.Tuple2;
import io.vertx.core.eventbus.EventBus;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProductService {

  @Inject
  EventBus publisher;

  @ConsumeEvent(value = "orders.product")
  void onInventoryEvent(OrderInfo order) {
    Log.infov("product service starts to handle order event: Order<{0}, {1}>", order.id, order.orderStatus);
    switch (order.orderStatus) {
      case RESERVE_INVENTORY:
        reserveOrder(order)
            .runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
            .onCompletion()
            .invoke(() -> publishOrderEvent(order.setStatus(OrderStatus.INVENTORY_SUCCESS)))
            .onFailure()
            .invoke((t) -> publishOrderEvent(order.setMessage(t.getMessage()).setStatus(OrderStatus.INVENTORY_FAILURE)))
            .subscribe()
            .with(
                    item -> Log.infov("product inventory reserved succeed with: {0}", item),
                    t -> Log.error("product inventory reserved failed", t));
        break;
      case REVERT_INVENTORY:
        revertOrder(order)
            .runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
            .onCompletion()
            .invoke(() -> publishOrderEvent(order.setStatus(OrderStatus.INVENTORY_REVERT_SUCCESS)))
            .onFailure()
            .invoke((t) -> publishOrderEvent(order.setMessage(t.getMessage()).setStatus(OrderStatus.INVENTORY_REVERT_FAILURE)))
            .subscribe()
            .with(
                    item -> Log.infov("product inventory reverted succeed with: {0}", item),
                    t -> Log.error("product inventory reverted failed with:" + order, t));
        break;
      default:
        break;
    }
  }

  void publishOrderEvent(OrderInfo order) {
    Log.infov("produce service publishes order event with: Order=<{0}, {1}>", order.id, order.orderStatus);
    publisher.publish("orders", order);
  }

  Multi<Product> reserveOrder(OrderInfo order) {
    Log.infov("product service starts to reserve order with: Order<{0}, {1}>", order.id, order.orderStatus);
    return Uni.createFrom().item(order)
            .onItem().transformToMulti(o -> Multi.createFrom().iterable(o.orderItems))
            .onItem().transformToUniAndMerge(this::findProduct)
            .onItem().transformToUniAndMerge(p -> operateStock(p, false))
            .onItem().transformToUniAndMerge(Product::updateAsync);
  }

  Multi<Product> revertOrder(OrderInfo order) {
    Log.infov("product service starts to revert order with: Order<{0}, {1}>", order.id, order.orderStatus);
    return Uni.createFrom().item(order)
            .onItem().transformToMulti(o -> Multi.createFrom().iterable(o.orderItems))
            .onItem().transformToUniAndMerge(this::findProduct)
            .onItem().transformToUniAndMerge(p -> operateStock(p, true))
            .onItem().transformToUniAndMerge(Product::updateAsync);
  }

  Uni<Tuple2<OrderItemInfo, Product>> findProduct(OrderItemInfo item) {
    return Uni.createFrom().item(Tuple2.of(item, Product.findById(item.productId)));
  }

  Uni<Product> operateStock(Tuple2<OrderItemInfo, Product> term, boolean revert) {
    return Uni.createFrom().item(term)
            .onItem().transform(t -> {
              var l = t.getItem1();
              var p = t.getItem2();
              if (!revert) {
                if (p.stock >= l.quantity) {
                  p.stock -= l.quantity;
                } else {
                  throw new RuntimeException("Product is out of sock with pid = " + p.id);
                }
              } else {
                p.stock += l.quantity;
              }
              return p;
            });
  }

}
