package dadflyblue.fruit;

import org.eclipse.microprofile.faulttolerance.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/fruit")
@RegisterRestClient(configKey = "fruity")
public interface FruityViceService {
  @GET
  @Path("/{name}")
  @Retry(maxRetries = 2, delay = 1000)
  @Fallback(FruityViceFallback.class)
  @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.75, delay = 5000)
  @Produces(MediaType.APPLICATION_JSON)
  FruityVice getFruitByName(@PathParam("name") String name);

  FruityVice EMPTY_FRUITY_VICE = FruityVice.of("empty", FruityVice.Nutritions.empty());

  class FruityViceFallback implements FallbackHandler<FruityVice> {
    @Override
    public FruityVice handle(ExecutionContext context) {
      return EMPTY_FRUITY_VICE;
    }
  }
}
