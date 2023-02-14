public class WorldMap {

  // specification: https://coursera.cs.princeton.edu/introcs/assignments/io/specification.php
  public static void main(String[] args) {
    int w = StdIn.readInt();
    int h = StdIn.readInt();

    StdDraw.setCanvasSize(w, h);
    StdDraw.setXscale(0, w);
    StdDraw.setYscale(0, h);

    while (!StdIn.isEmpty()) {
      StdIn.readString(); // ignore it
      int c = StdIn.readInt();

      double[] x = new double[c];
      double[] y = new double[c];

      for (int i = 0; i < c; i++) {
        x[i] = StdIn.readDouble();
        y[i] = StdIn.readDouble();
      }

      StdDraw.polygon(x, y);
    }
  }
}
