package dadflyblue.fruit;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

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

  @Transactional
  @POST
  public Response newFruit(Fruit fruit) {
    fruit.id = null;
    fruit.persist();
    return Response.status(Response.Status.CREATED).entity(fruit).build();
  }
}
