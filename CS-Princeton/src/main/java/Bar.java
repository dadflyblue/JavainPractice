public class Bar implements Comparable<Bar> {
  
  // Spec:
  // https://coursera.cs.princeton.edu/introcs/assignments/barchart/specification.php

  private final String name;
  private final int value;
  private final String category;

  // Creates a new bar.
  public Bar(String name, int value, String category) {
    if (name == null || value < 0 || category == null) {
      throw new IllegalArgumentException();
    }

    this.name = name;
    this.value = value;
    this.category = category;
  }

  // Returns the name of this bar.
  public String getName() {
    return name;
  }

  // Returns the value of this bar.
  public int getValue() {
    return value;
  }

  // Returns the category of this bar.
  public String getCategory() {
    return category;
  }

  // Compare two bars by value.
  public int compareTo(Bar that) {
    if (that == null) {
      throw new NullPointerException();
    }

    return this.value - that.value;
  }

  @Override
  public String toString() {
    return String.format("Bar(%s, %d, %s)", name, value, category);
  }

  // Sample client (see below).
  public static void main(String[] args) {
    Bar b1 = new Bar("a", 0, "test");
    Bar b2 = new Bar("b", 3, "test");
    System.out.println("b1: " + b1);
    System.out.println("b2: " + b2);
    System.out.println("b1 compare to b2: " + b1.compareTo(b2));
  }
}
