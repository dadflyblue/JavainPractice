public class RecursiveBinary {
  public static String convert(int n) {
    if (n == 1)
      return "1";
    else if (n == 0)
      return "0";

    return convert(n/2) + (n % 2);
  }

  public static void main(String[] args) {
    System.out.println(convert(66));
  }
}
