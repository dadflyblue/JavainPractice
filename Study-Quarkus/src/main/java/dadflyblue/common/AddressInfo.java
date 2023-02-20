package dadflyblue.common;

import dadflyblue.order.Address;

public class AddressInfo {
  public Long id;
  public String name;
  public String house;
  public String street;
  public String city;
  public String zip;

  public static AddressInfo from(Address s) {
    var address = new AddressInfo();
    {
      address.id = s.id;
      address.name = s.name;
      address.city = s.city;
      address.zip = s.zip;
      address.street = s.street;
      address.house = s.house;
    }
    return address;
  }

  @Override
  public String toString() {
    return "AddressInfo{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", house='" + house + '\'' +
            ", street='" + street + '\'' +
            ", city='" + city + '\'' +
            ", zip='" + zip + '\'' +
            '}';
  }
}
