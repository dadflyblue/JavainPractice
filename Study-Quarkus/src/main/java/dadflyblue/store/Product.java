package dadflyblue.store;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Product extends PanacheEntity {
  public String name;
  public Long price; // cents

  public static Product newProduct(String name, Long price, Long id) {
    Product p = new Product();
    {
      p.name = name;
      p.price = price;
      p.id = id;
    }
    return p;
  }
}
