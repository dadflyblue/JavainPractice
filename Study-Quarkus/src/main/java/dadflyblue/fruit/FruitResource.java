package dadflyblue.fruit;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/fruits")
public class FruitResource {

  @RestClient
  FruityViceService viceService;

  @GET
  public List<FruitDTO> fruits(@QueryParam("season") String season) {
    List<Fruit> fruits;
    if (season != null) {
      fruits = Fruit.findBySeason(season);
    } else {
      fruits = Fruit.listAll();
    }
    return fruits.parallelStream()
            .map(f -> FruitDTO.of(f, viceService.getFruitByName(f.name)))
            .collect(Collectors.toList());
  }

  @Transactional
  @POST
  public Response newFruit(Fruit fruit) {
    fruit.id = null;
    fruit.persist();
    return Response.status(Response.Status.CREATED).entity(fruit).build();
  }
}
