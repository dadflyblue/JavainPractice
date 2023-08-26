package dadflyblue.shipping;

import io.smallrye.mutiny.Uni;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/shipments")
public class ShippingResource {

  @GET
  @Transactional
  public Uni<List<Shipment>> getAll() {
    return Uni.createFrom().item(Shipment::getAll);
  }
}
