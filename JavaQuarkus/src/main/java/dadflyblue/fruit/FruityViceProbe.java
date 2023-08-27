package dadflyblue.fruit;

import io.smallrye.health.checks.UrlHealthCheck;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@SuppressWarnings("unused")
public class FruityViceProbe {

  @ConfigProperty(name = "fruity/mp-rest/url")
  String fruityUrl;

  @Readiness
  HealthCheck healthCheck() {
    return new UrlHealthCheck(fruityUrl+"/api/fruit/pineapple")
            .name("Fruity health check").requestMethod("GET").statusCode(200);
  }

}
