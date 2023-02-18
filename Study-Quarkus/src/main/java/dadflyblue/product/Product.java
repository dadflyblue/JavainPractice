package dadflyblue.product;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;

@Entity(name = "products")
public class Product extends PanacheEntity {
  public String name;
  public Long price; // cents
  public String category;
  public Integer stock;

  public static Product newProduct(String name, Long price, String category, Integer stock, Long id) {
    Product p = new Product();
    {
      p.name = name;
      p.price = price;
      p.category = category;
      p.stock = stock;
      p.id = id;
    }
    return p;
  }

  public static Product save(Product product) {
    Product.persist(product);
    return product;
  }

  public static Uni<Product> saveAsync(Product product) {
    return Uni.createFrom().item(product).runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
            .onItem().transform(Product::save);
  }
}
