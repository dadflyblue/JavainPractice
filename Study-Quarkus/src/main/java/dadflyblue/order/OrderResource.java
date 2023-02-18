package dadflyblue.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/orders")
public class OrderResource {
  @Inject
  OrderService service;

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public Uni<Order> createOrder(Order order) {
    Log.infov("Create an order invoked with: {0}", order);
    return service.createOrder(order)
            .onFailure().transform(t -> {
              Log.errorv("Create order failed with: {0}" + order, t);
              return new WebApplicationException("Order creating failed with: " + order, t);
            });
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Multi<Order> orders() {
    Log.info("Get all orders invoked.");
    return Order.getAllAsync();
  }

}
