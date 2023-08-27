package dadflyblue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

// From: https://docs.oracle.com/javase/tutorial/java/generics/QandE/generics-questions.html
public class Exercises {

  static interface Verify<T> {
    boolean test(T t);
  }

  static <E> int countIf(Collection<? extends E> l, Verify<? super E> v) {
    Objects.requireNonNull(l);
    int c = 0;

    for (E e : l) {
      if (v.test(e)) {
        ++c;
      }
    }
    return c;
  }

  // Write a generic method to count the number of elements in a collection that
  // have a specific property (for example, odd integers, prime numbers,
  // palindromes).
  static void s1() {
    Collection<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
    int c = countIf(l, (t) -> {
      Objects.requireNonNull(t);
      return t % 2 == 0;
    });
    System.out.println("count even numbers: " + c);
  }

  // Will the following class compile? If not, why?
  // Because Generic Type is non-primitvie type, it cannot be used with compare
  // operator.
  static final class Algorithm {
    public static <T extends Number> T max(T x, T y) {
      return x.doubleValue() > y.doubleValue() ? x : y;
    }
  }

  // this is an example how to avoid the limitation of 'Cannot Create Arrays of Parameterized Types'
  static <E> void swap1and2(E[] l) {
    if (l == null || l.length < 2) {
      throw new IllegalArgumentException();
    }

    // Object[] oa = l;
    // oa[0] = "hello"; // will cause runtime error but no warning at compile-time
    // maybe it is a solution that it's not allow to assign an arrays of parameterized types to Object[]

    E temp = l[0];
    l[0] = l[1];
    l[1] = temp;
  }

  // Write a generic method to exchange the positions of two different elements in
  // an array.
  static void s3() {
    Integer[] l = new Integer[] { 1, 2 };
    swap1and2(l);
    System.out.println("swap1and2: " + Arrays.toString(l));
  }

  public static void print(List<? extends Number> list) {
    for (Number n : list)
      System.out.print(n + " ");
    System.out.println();
  }

  static <E extends Comparable<E>> E maxInRange(List<? extends E> l, int start, int end) {
    Objects.requireNonNull(l);
    if (start < 0 || end < start || end > l.size()) {
      throw new IllegalArgumentException();
    }

    E max = l.get(start);
    for (int i = start + 1; i < end; ++i) {
      E e = l.get(i);
      if (e.compareTo(max) > 0) {
        max = e;
      }
    }
    return max;
  }

  // Write a generic method to find the maximal element in the range [begin, end)
  // of a list.
  static void s8() {
    List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
    int max = maxInRange(l, 2, 11);
    System.out.println("max in range: " + max);
    max = maxInRange(l, 0, 9);
    System.out.println("max in range: " + max);
  }

  static class Singleton<T> {

    public static <R> Singleton<R> getInstance() {
      if (instance == null)
        instance = new Singleton<R>();

      return (Singleton<R>) instance;
    }

    private static Object instance = null;
  }

  public static <T> int findFirst(List<Integer> l, int begin, int end, Verify<Integer> v) {
    for (int i = begin; i < end; ++i) {
      Integer e = l.get(i);
      if (v.test(e)) {
        return i;
      }
    }
    return -1;
  }

  static class FindRelativePrimeNumberVerify implements Verify<Integer> {
    final Collection<Integer> l;

    public FindRelativePrimeNumberVerify(Collection<Integer> l) {
      this.l = Objects.requireNonNull(l);
    }

    @Override
    public boolean test(Integer t) {
      if (l.size() == 0) {
        return false;
      }

      for (Integer e : l) {
        if (Utils.gcd(e, t) != 1) {
          return false;
        }
      }
      return true;
    }
  }

  static void s12() {
    List<Integer> l = Arrays.asList(3, 4, 6, 8, 11, 15, 28, 32);
    Collection<Integer> c = Arrays.asList(7, 18, 19, 25);
    Verify<Integer> v = new FindRelativePrimeNumberVerify(c);
    
    int p = findFirst(l, 0, l.size(), v);
    if (p != -1) {
      System.out.println(l.get(p) + " is first relatively primer number to " + c);
    }
  }

  public static void main(String[] args) {
    s1();
    s3();
    s8();
    s12();
  }
}
