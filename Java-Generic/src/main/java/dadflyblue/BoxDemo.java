package dadflyblue;

import java.util.ArrayList;
import java.util.List;

public class BoxDemo {

  public static <U extends Comparable<U>> void addBox(U value, List<? super Box<U>> list) {
    list.add(new Box<>(value));
  }

  public static <U extends Comparable<U>> void printBoxes(List<? extends Box<U>> list) {
    int count = 0;
    for (var box : list) {
      var c = box.get();
      System.out.println("Box #" + count + " contains [" +
          c + "]");
      count++;
    }
  }

  public static void main(String[] args) {
    var list = new ArrayList<Box<Integer>>();
    addBox(10, list);
    addBox(20, list);
    addBox(30, list);
    addBox(40, list);
    printBoxes(list);
  }
}
