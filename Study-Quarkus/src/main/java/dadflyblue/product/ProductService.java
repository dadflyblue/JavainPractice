package dadflyblue.product;

import dadflyblue.order.OrderItem;
import dadflyblue.order.Order;
import dadflyblue.order.OrderStatus;
import io.quarkus.logging.Log;
import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.smallrye.mutiny.tuples.Tuple2;
import io.vertx.core.eventbus.EventBus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class ProductService {

  @Inject
  EventBus publisher;

  @Transactional
  public Uni<Order> handleOrder(Order order) {
    Log.infov("handle order with: {0}", order);
    return Uni.createFrom().item(order)
            .onItem().transformToMulti(o -> Multi.createFrom().iterable(o.orderItems))
            .onItem().transformToUniAndMerge(this::findProduct)
            .onItem().transformToUniAndMerge(p -> dealWithStock(p, false))
            .onItem().transformToUniAndMerge(Product::saveAsync)
            .toUni().replaceWith(order);
  }

  @Transactional
  public Uni<Order> revertOrder(Order order) {
    Log.infov("revert order with: {0}", order);
    return Uni.createFrom().item(order)
            .onItem().transformToMulti(o -> Multi.createFrom().iterable(o.orderItems))
            .onItem().transformToUniAndMerge(this::findProduct)
            .onItem().transformToUniAndMerge(p -> dealWithStock(p, true))
            .onItem().transformToUniAndMerge(Product::saveAsync)
            .toUni().replaceWith(order);
  }

  Uni<Tuple2<OrderItem, Product>> findProduct(OrderItem item) {
    return Uni.createFrom().item(Tuple2.of(item, Product.<Product>findById(item.productId)))
            .runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
  }

  Uni<Product> dealWithStock(Tuple2<OrderItem, Product> term, boolean revert) {
    return Uni.createFrom().item(term).runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
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

  @ConsumeEvent(value = "orders")
  void onOrderEvent(Order order) {
    Log.infov("on order event received with: {0}", order);
    switch (order.orderStatus) {
      case RESERVE_INVENTORY:
        handleOrder(order).subscribe().with(
            o -> publisher.publish("orders",
                    o.setStatus(OrderStatus.INVENTORY_SUCCESS)),
            t -> publisher.publish("orders",
                    order.setStatus(OrderStatus.INVENTORY_FAILURE).setMessage(t.getMessage()))
        );
        break;
      case REVERT_INVENTORY:
        revertOrder(order).subscribe().with(
            o -> publisher.publish("orders",
                    o.setStatus(OrderStatus.INVENTORY_REVERT_SUCCESS)),
            t -> publisher.publish("orders",
                    order.setStatus(OrderStatus.INVENTORY_REVERT_FAILURE).setMessage(t.getMessage()))
        );
        break;
    }
  }

}
