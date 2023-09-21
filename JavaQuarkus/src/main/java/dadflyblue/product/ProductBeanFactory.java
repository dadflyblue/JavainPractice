package dadflyblue.product;

import io.smallrye.faulttolerance.api.FaultTolerance;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.Collections;

@ApplicationScoped
public class ProductBeanFactory {

  @ConfigProperty(name = "products.request.limit", defaultValue = "3")
  Integer productsRequestLimit;

  @Inject
  Logger logger;

  @Produces
  @Named("products")
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
