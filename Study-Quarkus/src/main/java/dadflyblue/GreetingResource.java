package dadflyblue;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.MeterRegistry;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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