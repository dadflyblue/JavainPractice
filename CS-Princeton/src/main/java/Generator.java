public class Generator {
  public static String randomString(int len, String alphabet) {
    if (alphabet == null || len < 0) {
      throw new IllegalArgumentException();
    }

    char[] chars = new char[len];
    for (int i = 0; i < len; i++) {
      int r = StdRandom.uniformInt(alphabet.length());
      chars[i] = alphabet.charAt(r);
    }
    return new String(chars);
  }

  // Generate n random strings of length l from a given alphabet
  public static String[] randomStrings(int n, int l, String alphabet) {
    if (alphabet == null || n < 0 || l < 0) {
      throw new IllegalArgumentException();
    }

    String[] ss = new String[n];
    for (int i = 0; i < n; i++) {
      ss[i] = randomString(l, alphabet);
    }
    return ss;
  }

  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int l = Integer.parseInt(args[1]);
    String alphabet = args[2];
    
    for (int i = 0; i < n; i++) {
      System.out.println(randomString(l, alphabet));
    }
  }
}
