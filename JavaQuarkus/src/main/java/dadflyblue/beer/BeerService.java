package dadflyblue.beer;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/v2")
@RegisterRestClient(configKey = "beers")
public interface BeerService {
  @GET
  @Timeout(5000)
  @Path("/beers")
  @Produces(MediaType.APPLICATION_JSON)
  Uni<List<Beer>> getBeers(@QueryParam("page") int page);
}
