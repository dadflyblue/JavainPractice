public class RightTriangle {

  // specification: https://coursera.cs.princeton.edu/introcs/assignments/hello/specification.php
  // faq: https://coursera.cs.princeton.edu/introcs/assignments/hello/faq.php

  public static void main(String[] args) {
    int i1 = Integer.parseInt(args[0]);
    int i2 = Integer.parseInt(args[1]);
    int i3 = Integer.parseInt(args[2]);

    long a = Math.min(i1, Math.min(i2, i3));
    long b = Math.max(Math.min(i1, i2), Math.min(Math.max(i1, i2), i3));
    long c = Math.max(i1, Math.max(i2, i3));

    System.out.println((a > 0) && (a*a + b*b == c*c));
  }
}
