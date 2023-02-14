public class ActivationFunction {

  // specification: https://coursera.cs.princeton.edu/introcs/assignments/functions/specification.php

  // Returns the Heaviside function of x.
  public static double heaviside(double x) {
    if (Double.isNaN(x)) {
      return Double.NaN;
    }

    if (x < 0) {
      return 0.0;
    } else if (x == 0) {
      return 0.5;
    } else {
      return 1.0;
    }
  }

  // Returns the sigmoid function of x.
  public static double sigmoid(double x) {
    if (Double.isNaN(x)) {
      return Double.NaN;
    }

    return 1 / (1 + Math.exp(-x));
  }

  // Returns the hyperbolic tangent of x.
  public static double tanh(double x) {
    if (Double.isNaN(x)) {
      return Double.NaN;
    }

    if (x == Double.NEGATIVE_INFINITY) {
      return -1.0;
    } else if (x == Double.POSITIVE_INFINITY) {
      return +1.0;
    } else if (x == Double.MAX_VALUE) {
      return +1.0;
    } else if (x == -Double.MAX_VALUE) {
      return -1.0;
    }

    return (Math.exp(x) - Math.exp(-x)) / (Math.exp(x) + Math.exp(-x));
  }

  // Returns the softsign function of x.
  public static double softsign(double x) {
    if (Double.isNaN(x)) {
      return Double.NaN;
    }

    if (x == Double.NEGATIVE_INFINITY) {
      return -1.0;
    } else if (x == Double.POSITIVE_INFINITY) {
      return +1.0;
    }

    return x / (1.0 + Math.abs(x));
  }

  // Returns the square nonlinearity function of x.
  public static double sqnl(double x) {
    if (Double.isNaN(x)) {
      return Double.NaN;
    }

    if (x <= -2) {
      return -1.0;
    } else if (-2 < x && x < 0) {
      return x + (x * x) / 4;
    } else if (0 <= x && x < 2) {
      return x - (x * x) / 4;
    } else {
      return 1.0;
    }
  }

  // Takes a double command-line argument x and prints each activation
  // function, evaluated, in the format (and order) given below.
  public static void main(String[] args) {
    double x = Double.parseDouble(args[0]);

    System.out.printf("heaviside(%.1f) = %.16f%n", x, heaviside(x));
    System.out.printf("sigmoid(%.1f) = %.16f%n", x, sigmoid(x));
    System.out.printf("tanh(%.1f) = %.16f%n", x, tanh(x));
    System.out.printf("softsign(%.1f) = %.16f%n", x, softsign(x));
    System.out.printf("sqnl(%.1f) = %.16f%n", x, sqnl(x));
  }
}
