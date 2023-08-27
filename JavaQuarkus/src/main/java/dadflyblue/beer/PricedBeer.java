package dadflyblue.beer;

import java.math.BigDecimal;

public class PricedBeer {
  public String name;
  public BigDecimal dollars;

  public static PricedBeer of(String name, long cents) {
    var b = new PricedBeer();
    {
      b.name = name;
      b.dollars = BigDecimal.valueOf(cents, 2);
    }
    return b;
  }
}
