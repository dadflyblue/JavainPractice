package dadflyblue.fruit;

public class FruityVice {
  public String name;
  public Nutritions nutritions;

  public static FruityVice of(String name, Nutritions nutritions) {
    var v = new FruityVice();
    {
      v.name = name;
      v.nutritions = nutritions;
    }
    return v;
  }

  public static class Nutritions {

    private static final Nutritions EMPTY = new Nutritions();
    public static Nutritions empty() {
      return EMPTY;
    }

    public double carbohydrates;
    public double calories;
  }
}
