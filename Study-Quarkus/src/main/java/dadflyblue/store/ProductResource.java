package dadflyblue.store;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.faulttolerance.api.FaultTolerance;
import org.eclipse.microprofile.faulttolerance.Bulkhead;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/products")
public class ProductResource {

  @Inject @Named("products")
  FaultTolerance<PageResult<Product>> guard;

  @GET
  @Transactional
  public PageResult<Product> getProducts(@QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize) {
    return guard.get(() -> {
      var query = Product.findAll().page(pageIndex, pageSize);
      return PageResult.newPage(pageIndex, pageSize, query.pageCount(), query.list());
    });
  }

  @GET
  @Path("{id}")
  @Transactional
  public Product getProduct(@NotNull @PathParam("id") Long id) {
    return Product.<Product>findByIdOptional(id).orElseThrow(NotFoundException::new);
  }

  @PUT
  @Transactional
  @Bulkhead(5)
  public Product newProduct(@NotNull Product product) {
    Product.persist(product);
    return product;
  }

  @POST
  @Transactional
  @Bulkhead(5)
  public Product updateProduct(@NotNull Product product) {
    Product.update("set name=?1, price=?2 where id=?3", product.name, product.price, product.id);
    return product;
  }

  @DELETE
  @Path("{id}")
  @Transactional
  @Bulkhead(5)
  public Response deleteProduct(@NotNull @PathParam("id") Long id) {
    if (Product.deleteById(id)) {
      return Response.ok().build();
    } else {
      throw new NotFoundException();
    }
  }
}
