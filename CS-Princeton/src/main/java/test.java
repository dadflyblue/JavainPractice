public class test {

  public static void Q4(int n)
  {
    if (n <= 0) return;
    StdOut.println(n);
    Q4(n-2);
    Q4(n-3);
    StdOut.println(n);
  }

  public static void main(String[] args) {
    Q4(7);
  }
}
