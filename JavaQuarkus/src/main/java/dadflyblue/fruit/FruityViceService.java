package dadflyblue.fruit;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.faulttolerance.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/fruit")
@RegisterRestClient(configKey = "fruity")
public interface FruityViceService {
  @GET
  @Path("/{name}")
  @Retry
  @Timeout // this timeout doesn't work very well.
  @Fallback(FruityViceFallback.class)
  @CircuitBreaker(requestVolumeThreshold = 10, failureRatio = 0.75, delay = 5000)
  @Produces(MediaType.APPLICATION_JSON)
  Uni<FruityVice> getFruitByName(@PathParam("name") String name);

  FruityVice EMPTY_FRUITY_VICE = FruityVice.of("empty", FruityVice.Nutritions.empty());

  class FruityViceFallback implements FallbackHandler<Uni<FruityVice>> {
    @Override
    public Uni<FruityVice> handle(ExecutionContext context) {
      Log.warn("fruity request falls back:", context.getFailure());
      return Uni.createFrom().item(EMPTY_FRUITY_VICE);
    }
  }
}
