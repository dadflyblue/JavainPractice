package dadflyblue.beer;

import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/beers")
public class BeerResource {

  @RestClient
  BeerService beerService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Multi<Beer> beers() {
    return Multi.createBy().repeating()
            .uni(
                  () -> new AtomicInteger(1),
                  i -> beerService.getBeers(i.getAndIncrement())
            )
            .until(List::isEmpty)
            .onItem().<Beer>disjoint()
            .select().where(b -> b.abv > 15.0);
  }
}
