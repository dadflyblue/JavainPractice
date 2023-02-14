public class PlotFunctionGraph {

  // Goal: Plot y = sin(4x) + sin(20x) in the interval (0, pi).
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);

    double[] x = new double[n+1];
    double[] y = new double[n+1];

    for (int i = 0; i <= n; i++) {
      x[i] = Math.PI * i / n; // split to n samples.
      y[i] = Math.sin(4*x[i]) + Math.sin(20*x[i]);
    }

    StdDraw.setXscale(0.0, Math.PI);
    StdDraw.setYscale(-2.0, +2.0);
    
    // note i control to less than n because we 
    // need to draw line from i to i+1
    for (int i = 0; i < n; i++) {
      StdDraw.line(x[i], y[i], x[i+1], y[i+1]);
    }
  }
}
