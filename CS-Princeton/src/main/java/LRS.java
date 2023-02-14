public class LRS {

  public static String lrs_brute_force(String s) {
    if (s == null) {
      throw new NullPointerException();
    }

    int n = s.length();
    String t = "";
    for (int i = 0; i < n; i++) {
      for (int j = i+1; j < n; j++) {
        String p = lcp(s.substring(i, n), s.substring(j, n));
        if (p.length() > t.length()) {
          t = p;
        }
      }
    }
    return t;
  }

  // Get largest repeated substring
  public static String lrs(String s) {
    if (s == null) {
      throw new NullPointerException();
    }

    int n = s.length();
    String[] ps = new String[n];
    for (int i = 0; i < n; i++) {
      ps[i] = s.substring(i, n);
    }

    Merge.sort(ps);
    String lrs = "";
    for (int i = 1; i < n; i++) {
      String t = lcp(ps[i-1], ps[i]);
      if (t.length() > lrs.length()) {
        lrs = t;
      }
    }
    return lrs;
  }
  
  // Get largest common prefix
  private static String lcp(String x, String y) {
    int n = Math.min(x.length(), y.length());

    for (int i = 0; i < n; i++) {
      if (x.charAt(i) != y.charAt(i)) {
        return x.substring(0, i);
      }
    }
    return x.substring(0, n);
  }

  public static void main(String[] args) {
    String s = Generator.randomString(1000, "actg");
    System.out.println(s);
    Stopwatch sw = new Stopwatch();
    System.out.println(lrs_brute_force(s) + " , elasped: " + sw.elapsedTime());
    sw = new Stopwatch();
    System.out.println(lrs(s) + " , elasped: " + sw.elapsedTime());
  }
}
