public class BandMatrix {

  // specification: https://coursera.cs.princeton.edu/introcs/assignments/loops/specification.php
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int w = Integer.parseInt(args[1]);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (Math.abs(j - i) > w) {
          System.out.print('0');
        } else {
          System.out.print("*");
        }
        System.out.print("  ");
      }
      System.out.println();
    }
  }
}
