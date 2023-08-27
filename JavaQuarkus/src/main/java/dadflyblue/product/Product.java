package dadflyblue.product;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.smallrye.mutiny.Uni;

import javax.persistence.Entity;
import javax.transaction.Transactional;

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

  @Transactional
  public static Uni<Product> updateAsync(Product product) {
    return Uni.createFrom().item(product).onItem()
            .transform(p -> {
              update("set name=?1, price=?2, category=?3, stock=?4 where id=?5",
                      p.name, p.price, p.category, p.stock, p.id);
              return p;
            });
  }
}
