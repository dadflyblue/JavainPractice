public class IFS {

  // Iterated function systems. 
  // An Iterated function system (IFS) is a general way to produce fractals 
  // like the Sierpinski triangle or Barnsley fern. As a first example,
  //  consider the following simple process: 
  // Start by plotting a point at one of the vertices of an equilateral triangle. 
  // Then pick one of the three vertices at random and plot a new point halfway between 
  // the point just plotted and that vertex. Continue performing the same operation.
  public static void main(String[] args) {
    int trails = Integer.parseInt(args[0]);

    // read probability distribution for each vertex
    double[] dist = StdArrayIO.readDouble1D();

    // read the update matrices
    double[][] xc = StdArrayIO.readDouble2D();
    double[][] yc = StdArrayIO.readDouble2D();

    // set start point
    double x = 0.0, y = 0.0;

    StdDraw.enableDoubleBuffering();

    for (int i = 0; i < trails; i++) {
      // pick a random vertex index
      int r = StdRandom.discrete(dist);

      double xx = xc[r][0] * x + xc[r][1] * y + xc[r][2];
      double yy = yc[r][0] * x + yc[r][1] * y + yc[r][2];

      x = xx;
      y = yy;

      StdDraw.point(x, y);

      if (i % 100 == 0) {
        StdDraw.show();
        StdDraw.pause(10);
      } 
    }

    StdDraw.show();
  }
}
