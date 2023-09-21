package dadflyblue;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.Calendar;
import java.util.TimeZone;

@Path("/hello")
public class GreetingResource {

  @ConfigProperty(name = "greeting", defaultValue = "Hello")
  String greeting;

  @Inject
  MeterRegistry registry;

  @PostConstruct
  void init() {
    registry.gauge("offsetFromUTC", this, GreetingResource::offsetFromUTC);
  }

  int offsetFromUTC() {
    return TimeZone.getDefault().getOffset(Calendar.ZONE_OFFSET)/(3600*1000);
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Counted
  public String hello() {
      return greeting;
  }
}