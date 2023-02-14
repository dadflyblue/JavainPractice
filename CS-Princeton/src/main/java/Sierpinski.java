public class Sierpinski {
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);

    // set coordinates of a, b, c : vertices of an equilateral triangle.
    // a: [0.0, 0.0]
    // b: [1.0, 0.0]
    // c: [0.5, Math.sqrt(1 - 0.25)]

    double[] xc = { 0.0, 1.0, 0.5 };
    double[] yc = { 0.0, 0.0, Math.sqrt(1 - 0.25) };

    double x = 0.0, y = 0.0;
    
    for (int i = 0; i < n; i++) {
      int r = StdRandom.uniformInt(3);
      x = (x + xc[r]) / 2.0;
      y = (y + yc[r]) / 2.0;

      StdDraw.point(x, y);
    }

  }
}
