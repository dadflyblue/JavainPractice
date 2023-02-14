public class FourierSpikes {

  // Question 2.1.29 from: 
  // https://www.coursera.org/learn/cs-programming-java/supplement/wS82C/optional-enrichment-on-functions-and-libraries
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);

    StdDraw.setXscale(-11, +11);
    StdDraw.setYscale(-0.1, 1.1);
    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);

    double t = -10.0;
    double step = 20.0 / 500;
    double[] p = new double[500];

    for (int i = 0; i < 500; i++) {
      double sum = 0.0;
      for (int j = 1; j <= n; j++) {
        sum += Math.cos(j*t);
      }
      p[i] = sum / n;
      t += step;
    }
    
    StdStats.plotLines(p);
  }
}
