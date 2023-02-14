public class Bernoulli {

  // Returns number of heads when flipping n biased-p coins.
  public static int binomial(int n, double p) {
    int heads = 0;
    for (int i = 0; i < n; i++) {
      if (StdRandom.bernoulli(p)) {
        heads++;
      }
    }
    return heads;
  }

  // Returns number of heads when flipping n fair coins.
  public static int binomial(int n) {
    int heads = 0;
    for (int i = 0; i < n; i++) {
      if (StdRandom.bernoulli()) {
        heads++;
      }
    }
    return heads;
  }


  public static void main(String[] args) {
    int n  = Integer.parseInt(args[0]);
    int trails = Integer.parseInt(args[1]);

    StdDraw.setYscale(0, 0.2);

    // flip n fair coin, trails times
    int[] freq = new int[n+1];
    for (int t = 0; t < trails; t++) {
        freq[binomial(n)]++;
    }

    // plot normalized values
    double[] normals = new double[n+1];
    for (int i = 0; i <= n; i++) {
      normals[i] = (double) freq[i] / trails;
    }

    // draw the freqence of n fair coin flipping of trails times
    StdStats.plotBars(normals);

    // plot Gaussian approximation
    double mean = n / 2.0;
    double stddev = Math.sqrt(n) / 2.0;
    double[] phi = new double[n+1];
    
    for (int i = 0; i <= n; i++) {
      phi[i] = Gaussian.pdf(i, mean, stddev);
    }
    
    // draw the Gaussian pdf results
    StdStats.plotLines(phi);
  }
}
