package dadflyblue.product;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Product extends PanacheEntity {
  public String name;
  public Long price; // cents
  public String category;

  public static Product newProduct(String name, Long price, String category, Long id) {
    Product p = new Product();
    {
      p.name = name;
      p.price = price;
      p.category = category;
      p.id = id;
    }
    return p;
  }
}
