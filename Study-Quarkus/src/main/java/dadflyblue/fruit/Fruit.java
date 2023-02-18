package dadflyblue.fruit;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.infrastructure.Infrastructure;

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
    return Multi.createFrom().<Fruit>iterable(listAll())
            .runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
  }

  public static Multi<Fruit> findBySeasonAsync(String season) {
    return Multi.createFrom()
            .<Fruit>iterable(find("upper(season)", season.toUpperCase()).list())
            .runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
  }
}
