package dadflyblue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeapPollutionExample {

  static class ArrayBuilder {
    @SafeVarargs
    static <T> void addToList(List<T> list, T... elems) {
      for (T e : elems) {
        list.add(e);
      }
    }

    static void faultyMethod(List<String>... l) {
      Object[] os = l;
      os[0] = Arrays.asList(4);
      System.out.println("l[0].get(0): " + l[0].get(0));
    }
  }

  public static void main(String[] args) {
    List<String> la = new ArrayList<>();
    List<String> lb = new ArrayList<>();
    
    ArrayBuilder.addToList(la, "One", "Two", "Three");
    ArrayBuilder.addToList(lb, "Four", "Five", "Six");

    List<List<String>> ll = new ArrayList<>();
    ArrayBuilder.addToList(ll, la, lb);
    ArrayBuilder.faultyMethod(Arrays.asList("Hello!"), Arrays.asList("World!"));
  }
}
