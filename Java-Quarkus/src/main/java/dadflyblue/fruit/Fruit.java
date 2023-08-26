package dadflyblue.fruit;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.smallrye.mutiny.Multi;

import javax.persistence.Entity;

@Entity(name = "fruits")
public class Fruit extends PanacheEntity {
  public String name;
  public String season;

  public static Fruit of(String name, String season) {
    var f = new Fruit();
    {
      f.season = season;
      f.name = name;
    }
    return f;
  }

  public static Multi<Fruit> findAllAsync() {
    return Multi.createFrom().items(
            () -> Fruit.<Fruit>listAll().stream());
  }

  public static Multi<Fruit> findBySeasonAsync(String season) {
    return Multi.createFrom().items(
            () -> Fruit.<Fruit>find("upper(season)", season.toUpperCase()).list().stream());
  }
}
