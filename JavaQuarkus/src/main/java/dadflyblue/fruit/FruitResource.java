package dadflyblue.fruit;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/fruits")
public class FruitResource {

  @RestClient
  FruityViceService viceService;

  @GET
  @Transactional
  public Multi<FruitDTO> fruits(@QueryParam("season") String season) {
    Multi<Fruit> fruits;
    if (season != null) {
      fruits = Fruit.findBySeasonAsync(season);
    } else {
      fruits = Fruit.findAllAsync();
    }

    return fruits.onItem()
            .transformToUniAndConcatenate(this::fromFruit);
  }

  private Uni<FruitDTO> fromFruit(Fruit fruit) {
    return viceService.getFruitByName(fruit.name)
            .onItem().transform(f -> FruitDTO.of(fruit, f));
  }

  @POST
  @Transactional
  public Uni<Response> newFruit(Fruit fruit) {
    fruit.id = null;
    fruit.persist();
    return Uni.createFrom().item(
            Response.status(Response.Status.CREATED).entity(fruit).build());
  }
}
