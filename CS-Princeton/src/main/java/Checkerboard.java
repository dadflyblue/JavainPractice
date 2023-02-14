public class Checkerboard {

  // specification: https://coursera.cs.princeton.edu/introcs/assignments/io/specification.php
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);

    StdDraw.setScale(0, n);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i % 2 == 0) {
          if (j % 2 == 0) {
            StdDraw.setPenColor(StdDraw.BLUE);
          } else {
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
          }
        } else {
          if (j % 2 == 0) {
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
          } else {
            StdDraw.setPenColor(StdDraw.BLUE);
          }
        }

        double x = i + .50;
        double y = j + .50;

        StdDraw.filledSquare(x, y, 0.5);
      }
    }
  }
}
