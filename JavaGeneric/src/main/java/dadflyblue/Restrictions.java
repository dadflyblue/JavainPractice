package dadflyblue;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Restrictions {

  static class Pair<K, V> {

    private K key;
    private V value;

    public Pair(K key, V value) {
      this.key = key;
      this.value = value;
    }

    // ...
  }

  static void instantiate() {
    // Pair<int, char> p = new Pair<>(1, 'a'); // compile-time error
    Pair<Integer, Character> pair = new Pair<>(1, 'a');
    List<String> ls = new ArrayList<>();
    try {
      appendOne(ls, String.class);
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
        | NoSuchMethodException | SecurityException e) {
      throw new RuntimeException(e);
    }
    System.out.println("ls: " + ls);
  }

  static <E> void appendOne(List<E> l, Class<E> cls) 
    throws InstantiationException, IllegalAccessException, IllegalArgumentException,
     InvocationTargetException, NoSuchMethodException, SecurityException {
    // E e = new E(); // compile-time error
    E e = cls.getDeclaredConstructor(cls).newInstance("hello");
    l.add(e);
  }

  static void casts() {
    List<Integer> il = new ArrayList<>();
    List<?> nl = il;

    List<String> sl = new ArrayList<>();
    // List<String> sl = new LinkedList<>(); // will cause runtime error - ClassCastException, when casting type
    ArrayList<String> sal = (ArrayList<String>) sl;
    sal.add("hello casts");
    System.out.println("sal: " + sal);
  }
  
  public static void main(String[] args) {
    instantiate();
    casts();
  }

}
