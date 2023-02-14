public class Newton {
  public static double sqrt(double x, double eps) {
    if (x < 0) {
      return Double.NaN;
    }

    double t = x;
    double EPS = eps; // epsilon for error tolerance

    while (Math.abs(t - x / t) > EPS * t) {
      t = (t + x / t) / 2.0;
    }

    return t;
  }
}
