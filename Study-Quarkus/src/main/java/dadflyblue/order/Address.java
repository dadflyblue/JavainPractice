package dadflyblue.order;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity(name = "addresses")
public class Address extends PanacheEntity {
  public String name;
  public String house;
  public String street;
  public String city;
  public String state;
  public String zip;

  public static Address of(Long id, String name, String house, String street, String city, String state, String zip) {
    Address a = new Address();
    {
      a.id = id;
      a.city = city;
      a.house = house;
      a.street = street;
      a.state = state;
      a.zip = zip;
      a.name = name;
    }
    return a;
  }
}
