public class AnimatedHistogram {

  // Question 1.5.30 from: 
  // https://www.coursera.org/learn/cs-programming-java/supplement/rZAs3/optional-enrichment-on-input-and-output
  public static void main(String[] args) {
    int n = 5;
    int trails = 1000;
    double lo = 1;
    double hi = 5;

    StdDraw.setCanvasSize(1000, 500);
    StdDraw.enableDoubleBuffering();
    StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);

    double[] freq = new double[n];
    double max = 0.0;
    for (int t = 0; t < trails; t++) {
      double r = Math.random() * (hi - lo);
      int p = (int) ((r * n) / (hi - lo));
      freq[p]++;
      if (freq[p] > max) {
        max = freq[p];
      }

      StdDraw.setYscale(-1, max+10);
      StdDraw.setXscale(-1, n);

      for (int i = 0; i < n; i++) {
        StdDraw.filledRectangle(i, freq[i] / 2.0, 0.5 / 2.0, freq[i] / 2.0);
      }

      StdDraw.show();
      StdDraw.pause(20);
    }
  }
}
