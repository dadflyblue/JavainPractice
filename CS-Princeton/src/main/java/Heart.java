public class Heart {
  public static void main(String[] args) {
    StdDraw.setXscale(-1.5, +1.5);
    StdDraw.setYscale(-1.5, +1.5);
    StdDraw.setPenColor(StdDraw.PINK);

    // Firstly, draw a diamond
    double[] x = { -1.0, 0.0, 1.0, 0.0 };
    double[] y = { +0.0, -1.0, 0.0, 1.0 };
    StdDraw.filledPolygon(x, y);

    // Then, draw two circles on the upper left and upper right sides.
    StdDraw.filledCircle(-.5, .5, Math.sqrt(2) / 2);
    StdDraw.filledCircle(.5, .5, Math.sqrt(2) / 2);
  }
}
