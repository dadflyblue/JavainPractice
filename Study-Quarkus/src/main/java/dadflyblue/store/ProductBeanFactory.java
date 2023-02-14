package dadflyblue.store;

import io.smallrye.faulttolerance.api.FaultTolerance;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;

@ApplicationScoped
@SuppressWarnings("unused") // used by cdi
public class ProductBeanFactory {

  @ConfigProperty(name = "products.request.limit", defaultValue = "3")
  Integer productsRequestLimit;

  @Inject
  Logger logger;

  @Produces
  @Named("products")
  @SuppressWarnings("unused") // used by @Inject
  public FaultTolerance<PageResult<Product>> createProductFaultTolerance() {
    return FaultTolerance.<PageResult<Product>>create()
            .withFallback()
            .handler((t) -> {
                logger.warn("requesting products falls back to empty list", t);
                return PageResult.newPage(0, 0, 0, Collections.emptyList());
              }).done()
            .withBulkhead().limit(productsRequestLimit).done()
            .build();
  }
}
