public class Binary {

  // Recursive version of binary convertion.
  public static String rbin(int n) {
    if (n == 0) {
      return "";
    }
    return rbin(n / 2) + (n % 2);
  }

  // Non-recursive version of binary convertion.
  public static String nrbin(int n) {
    String result = "";
    while (n != 0) {
      result = (n % 2) + result;
      n = n / 2;
    }
    return result;
  }

  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
  
    System.out.println(nrbin(n));
    System.out.println(rbin(n));
  }
}
