package dadflyblue.common;

import dadflyblue.order.Address;

import java.text.MessageFormat;

public class AddressInfo {
  public Long id;
  public String name;
  public String house;
  public String street;
  public String city;
  public String state;
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
      address.state = s.state;
    }
    return address;
  }

  @Override
  public String toString() {
    return "AddressInfo{" + getFormattedShippingAddress() + "}";
  }

  public String getFormattedShippingAddress() {
    return MessageFormat.format("{0}, {1} {2}, {3}, {4}, {5}, {6}", id, name, house,
            street, city, state, zip);
  }
}
