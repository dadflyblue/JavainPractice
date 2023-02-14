public class Kary {
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int b = Integer.parseInt(args[1]);
    
    String result = "";
    
    for (;;) {
      int r = n % b;
      n = n / b;
      result = convertoChar(r) + result;

      if (n == 0)
        break;
    }
    
    System.out.println(result);
  }

  private static char convertoChar(int r) {
    int charA = (int) 'A';
    int char0 = (int) '0';
    return r < 10 ? (char) (char0 + r) : (char) (charA + r - 10);
  }
}
