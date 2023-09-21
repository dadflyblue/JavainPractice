package dadflyblue.shipping;

import io.smallrye.mutiny.Uni;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.util.List;

@Path("/shipments")
public class ShippingResource {

  @GET
  @Transactional
  public Uni<List<Shipment>> getAll() {
    return Uni.createFrom().item(Shipment::getAll);
  }
}
