package dadflyblue.fruit;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.util.List;

@Entity
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

  public static List<Fruit> findBySeason(String season) {
    return find("upper(season)", season.toUpperCase()).list();
  }
}
