package dadflyblue;

import java.util.ArrayList;
import java.util.List;

public class WildcardErrorBad {

  void foo(List<?> list) {
//    list.set(0, list.get(0));
    fooHelper(list);
  }

  <E> void fooHelper(List<E> list) {
    list.set(0, list.get(0));
  }

//  void swapFirst(List<? extends Number> l1, List<? extends Number> l2) {
//    Number temp = l1.get(0);
//    l1.set(0, l2.get(0));
//    l2.set(0, temp);
//  }

  public static void main(String[] args) {
    List<Double> ld = new ArrayList<Double>();
    List<? extends Number> ln = ld;
    // ln.add(1); compile error
    ln.add(null);
    for (Number n : ln) {
      System.out.println("n: " + n);
    }

    List<? super Double> lwd = ld;
    // lwd.add(1); compile error
    lwd.add(3.14);
    for (Number n : ln) {
      System.out.println("n: " + n);
    }
  }

}
