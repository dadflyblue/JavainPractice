
public class TestSearch {

  public static int sequentialSearch(String k, String[] a) {
    if (k == null || a == null) {
      throw new IllegalArgumentException();
    }

    for (int i = 0; i < a.length; i++) {
      if (k.equals(a[i])) {
        return i;
      }
    }
    return -1;
  }

  public static int binarySearch(String k, String[] a) {
    if (k == null || a == null) {
      throw new IllegalArgumentException();
    }

    int low = 0;
    int hi = a.length;
    while (low < hi) {
      int mid = low + (hi - low) / 2;
      if (a[mid].compareTo(k) > 0) {
        hi = mid;
      } else if (a[mid].compareTo(k) < 0) {
        low = mid;
      } else {
        return mid;
      }
    }
    return -1;
  }

  private static void testSS(int n, String[] words) {
    for (int i = 0; i < n*10; i++) {
      int r = StdRandom.uniformInt(n);
      String k = words[r];
      if (sequentialSearch(k, words) != -1) {
        System.out.println(k);
      }
    }
  }

  private static void testBinary(int n, String[] words) {
    Merge.sort(words);
    for (int i = 0; i < n*10; i++) {
      int r = StdRandom.uniformInt(n);
      String k = words[r];
      if (binarySearch(k, words) != -1) {
        System.out.println(k);
      }
    }
  }

  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int l = Integer.parseInt(args[1]);
    String alphabet = args[2];

    String[] words = Generator.randomStrings(n, l, alphabet);
    double start = System.currentTimeMillis() / 1000.0;
    testBinary(n, words);
    double end = System.currentTimeMillis() / 1000.0;
    System.out.println(Math.round(end-start) + " seconds");
  }
  
}