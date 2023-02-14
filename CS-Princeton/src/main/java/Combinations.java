
public class Combinations {
  
  public static void combin1(String prefix, String s) {
    if (s.length() > 0) {
      char c = s.charAt(0);
      System.out.println(prefix + c);
      combin1(prefix + c, s.substring(1));
      combin1(prefix, s.substring(1));
    }
  }

  /**
   * <p>
   * Source: 
   * https://www.coursera.org/learn/cs-programming-java/supplement/yvKrY/optional-enrichment-on-recursion
   * </p>
   * 
   * 2.3.19 Combinations. Write a program Combinations that takes an integer
   *  command-line argument n and prints all 2^n combinations of any size. 
   * A combination is a subset of the n elements, independent of order. As an example,
   *  when n = 3, you should get the following output: a ab abc ac b bc c  
   * Note that your program needs to print the empty string (subset of size 0).
   * 
   * @param prefix track the prefix of combinations
   * @param s the left over string of elements
   */
  public static void combin2(String prefix, String s) {
    System.out.println(prefix);
    for (int i = 0; i < s.length(); i++) {
      combin2(prefix + s.charAt(i), s.substring(i+1));
    }
  }

  public static void combin2k(String prefix, String s, int k) {
    if (k == 0) {
      System.out.println(prefix);
    } else {
      for (int i = 0; i < s.length(); i++) {
        combin2k(prefix + s.charAt(i), s.substring(i+1), k-1);
      }
    }
  }

  public static void combK(int[] ps, int cur, int next, String s, int k) {
    if (k == 0) {
      print(ps);
    } else {
      for (int i = next; i < s.length(); i++) {
        ps[cur] = i;
        combK(ps, cur+1, i+1, s, k-1);
      }
    }
  }

  private static void print(int[] a) {
    for (int i = 0; i < a.length; i++) {
      System.out.print(a[i] + " ");
    }
    System.out.println();
  }
  
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    if (n > alphabet.length()) {
      System.out.println("n cannot be great than " + alphabet.length());
      return;
    }
    
    String elements = alphabet.substring(0, n);
    combin1("", elements);
    System.out.println();
    combin2("", elements);
    System.out.println();
    combin2k("", elements, 3);
    System.out.println();
    int[] ps = new int[3]; // 3 points
    combK(ps, 0, 0, elements, 3);
  }
}
