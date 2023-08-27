package dadflyblue.fruit;

public class FruitDTO {
  public String name;
  public String season;
  public double carbohydrates;
  public double calories;

  public static FruitDTO of(Fruit fruit, FruityVice vice) {
    var f = new FruitDTO();
    {
      f.calories = vice.nutritions.calories;
      f.carbohydrates = vice.nutritions.carbohydrates;
      f.name = fruit.name;
      f.season = fruit.season;
    }
    return f;
  }
}
