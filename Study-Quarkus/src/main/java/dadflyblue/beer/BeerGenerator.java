package dadflyblue.beer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
@SuppressWarnings("unused")
public class BeerGenerator {
  @RestClient
  BeerService beerService;

  @Inject
  ObjectMapper mapper;

  @Outgoing("beers")
  Multi<Beer> beers() {
    List<Beer> beers = beerService.getBeers(10).await().indefinitely();
    return Multi.createFrom().ticks()
            .every(Duration.ofSeconds(1))
            .onOverflow().drop()
            .map(t -> beers.get(ThreadLocalRandom.current().nextInt(0, beers.size())));
  }

  @Incoming("beers")
  @Outgoing("groups")
  public Multi<List<Beer>> group(Multi<Beer> stream) {
    return stream.group().intoLists().every(Duration.ofSeconds(3));
  }

  @Incoming("groups")
  @Outgoing("messages")
  public String processGroup(List<Beer> list) {
    try {
      return mapper.writeValueAsString(list);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Incoming("messages")
  public void print(String msg) {
    Log.infov("beers: {0}", msg);
  }
}
