package dadflyblue.order;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orders")
public class OrderResource {
  @Inject
  OrderService service;

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public Uni<Order> createOrder(Order order) {
    Log.infov("create an order invoked with: {0}", order);
    return service.createOrder(order)
            .onFailure().transform(t -> {
              Log.errorv("create order failed with: {0}" + order, t);
              return new WebApplicationException("order creating failed with: " + order, t);
            });
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public Multi<Order> orders() {
    Log.info("Get all orders invoked.");
    return Order.getAllAsync();
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public Uni<Response> deleteOrder(@PathParam("id") Long id) {
    return Uni.createFrom().item(id)
            .onItem().transform(Order::getById)
            .onItem().ifNull().failWith(new NotFoundException("order not found"))
            .onItem().transform(o -> {
              Order.removeById(o.id);
              return Response.ok().build();
            });
  }

}
