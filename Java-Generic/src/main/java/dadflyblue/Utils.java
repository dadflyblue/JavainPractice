package dadflyblue;

import java.io.Serializable;
import java.util.*;

public class Utils {
  private Utils() {
  }

  public static <T extends Object & Comparable<? super T>> int countGreatThan(T[] arr, T elem) {
    if (arr == null || arr.length == 0) {
      return 0;
    }

    int count = 0;
    for (T e : arr) {
      if (e.compareTo(elem) > 0) {
        count++;
      }
    }
    return count;
  }

  public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll) {
    Objects.requireNonNull(coll);
    Iterator<? extends T> it = coll.iterator();
    T max = it.next();

    while (it.hasNext()) {
      T t = it.next();
      if (t.compareTo(max) > 0) {
        max = t;
      }
    }
    return max;
  }

  public static <T> T pick(T a1, T a2) {
    return a2;
  }

  public static double sumOfList(List<? extends Number> list) {
    double sum = 0.0;
    for (var elem : list) {
      sum += elem.doubleValue();
    }
    return sum;
  }

  public static void printList(List<?> list) {
    boolean first = true;
    for (var elem : list) {
      if (first) {
        System.out.print("[");
        first = false;
      }
      System.out.print(" " + elem);
    }
    System.out.println(" ]");
  }

  private static final Random RANDOM = new Random();

  public static void fillWithRandomNumbers(List<? super Integer> list, int size) {
    for (int i = 1; i <= size; ++i) {
      list.add(RANDOM.nextInt(100));
    }
  }

  public static int gcd(int x, int y) {
    if (x <= 0 || y <= 0) {
      throw new IllegalArgumentException();
    }

    for (;;) {
      int r = x % y;
      if (r == 0)
        break;
      x = y;
      y = r;
    }
    return y;
  }

  public static void main(String[] args) {
    System.out.println("sum: " + sumOfList(Arrays.asList(1, 2, 3, 4, 5)));
    System.out.println("sum: " + sumOfList(Arrays.asList(1.1, 2.2, 3.3, 4.4, 5.5)));
    var arr = new Integer[]{0, 1, 3, 4, 8, 11, 33};
    System.out.printf("count: %d%n", countGreatThan(arr, 2));
    printList(Arrays.asList(arr));
    Serializable d = pick("d", new ArrayList<String>());

    List<Number> numbers = new ArrayList<>();
    fillWithRandomNumbers(numbers, 10);
    printList(numbers);

    List<Integer> integers = new ArrayList<>();
    fillWithRandomNumbers(integers, 10);
    printList(integers);

    List<Object> objects = new ArrayList<>();
    fillWithRandomNumbers(objects, 10);
    printList(objects);
  }
}
