package dadflyblue;

import java.util.ArrayList;
import java.util.Collection;

public class Main {

  static class MyClass<T> {
    public <U> MyClass(U u, T t) {
      System.out.println("U: " + u.getClass().getName());
      System.out.println("T: " + t.getClass().getName());
    }
  }

  public static void main(String[] args) {
    MyClass<Integer> m = new MyClass<>("", 11);

    Collection<String> cs = new ArrayList<>();
    // cs = new ArrayList<Integer>(); // type incompatible error

    Collection<? extends Number> numbers = new ArrayList<Number>();
    numbers = new ArrayList<Integer>();
    Collection<? extends Integer> integers = new ArrayList<Integer>();
    numbers = integers;
  }
}