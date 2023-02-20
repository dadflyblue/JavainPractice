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

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@SuppressWarnings("unused")
public class ProductService {

  @Inject
  EventBus publisher;

  @ConsumeEvent(value = "orders.produce")
  void onOrderEvent(OrderInfo order) {
    Log.infov("on order event received with: {0}", order);
    switch (order.orderStatus) {
      case RESERVE_INVENTORY:
        reserveOrder(order)
                .runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
                .onCompletion().invoke(
                        () -> publisher.publish("orders",
                                order.setStatus(OrderStatus.INVENTORY_SUCCESS)))
                .onFailure().invoke(
                        (t) -> publisher.publish("orders",
                                order.setStatus(OrderStatus.INVENTORY_FAILURE).setMessage(t.getMessage())))
                .subscribe().with(
                        item -> Log.infov("product inventory reserved succeed with: {0}", item),
                        t -> Log.error("product inventory reserved failed", t)
                );
        break;
      case REVERT_INVENTORY:
        revertOrder(order)
                .runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
                .onCompletion().invoke(
                        () -> publisher.publish("orders",
                                order.setStatus(OrderStatus.INVENTORY_REVERT_SUCCESS)))
                .onFailure().invoke(
                        t -> publisher.publish("orders",
                                order.setStatus(OrderStatus.INVENTORY_REVERT_FAILURE).setMessage(t.getMessage()))
                )
                .subscribe().with(
                        item -> Log.infov("product inventory reverted succeed with: {0}", item),
                        t -> Log.error("product inventory reverted failed", t)
                );
        break;
    }
  }

  Multi<Product> reserveOrder(OrderInfo order) {
    Log.infov("reserve order with: Order<{0}>", order.id);
    return Uni.createFrom().item(order)
            .onItem().transformToMulti(o -> Multi.createFrom().iterable(o.orderItems))
            .onItem().transformToUniAndMerge(this::findProduct)
            .onItem().transformToUniAndMerge(p -> operateStock(p, false))
            .onItem().transformToUniAndMerge(Product::updateAsync);
  }

  Multi<Product> revertOrder(OrderInfo order) {
    Log.infov("revert order with: Order<{0}>", order.id);
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
