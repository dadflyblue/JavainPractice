public class Insertion {

  public static void sort(String[] ss) {
    if (ss == null) {
      throw new NullPointerException();
    }

    for (int i = 1; i < ss.length; i++) {
      for (int j = i-1; j >= 0; j--) {
        if (ss[j].compareTo(ss[j+1]) > 0) {
          swap(ss, j, j+1);
        } else {
          break;
        }
      }
    }
  }

  private static void swap(String[] ss, int i, int j) {
    String t = ss[i];
    ss[i] = ss[j];
    ss[j] = t;
  }

  public static void main(String[] args) {
    String[] ss = Generator.randomStrings(100, 5, "abcdefghijklmnopqrstuvwxyz");
    sort(ss);
    for (int i = 0; i < ss.length; i++) {
      System.out.println(ss[i]);
    }
  }
}
