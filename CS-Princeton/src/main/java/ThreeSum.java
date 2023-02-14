public class ThreeSum {
  
  // three-sum brute force algorithm
  public static int bruteCount(int[] a) {
    int count = 0;
    for (int i = 0; i < a.length; i++) {
      for (int j = i + 1; j < a.length; j++) {
        for (int t = j + 1; t < a.length; t++) {
          if (a[i] + a[j] + a[t] == 0) {
            count++;
          }
        }
      }
    }
    return count;
  }

  private static int[] generate(int n, int m) {
    int[] a = new int[n];
    for (int i = 0; i < a.length; i++) {
      a[i] = StdRandom.uniformInt(-m, m);
    }
    return a;
  }

  public static void main(String[] args) {
    int m = Integer.parseInt(args[0]);
    int n = Integer.parseInt(args[1]);
    String method = args[2];
    
    double start = System.currentTimeMillis() / 1000.0;
    int count = 0;
    if ("brute".equals(method)) {
      count = bruteCount(generate(n, m));
    }
    double end = System.currentTimeMillis() / 1000.0;
    StdOut.printf("%d (%.0f seconds)%n", count, end - start);
  }
}
