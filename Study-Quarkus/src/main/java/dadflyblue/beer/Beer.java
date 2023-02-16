package dadflyblue.beer;

public class Beer {
  public String name;
  public String tagline;
  public double abv;

  public static Beer of(String name, String tagline, double abv) {
    var b = new Beer();
    {
      b.abv = abv;
      b.tagline = tagline;
      b.name = name;
    }
    return b;
  }
}
