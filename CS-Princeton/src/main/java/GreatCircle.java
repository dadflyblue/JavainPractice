public class GreatCircle {

  // specification: https://coursera.cs.princeton.edu/introcs/assignments/hello/specification.php
  // faq: https://coursera.cs.princeton.edu/introcs/assignments/hello/faq.php

  public static void main(String[] args) {
    double x1 = Double.parseDouble(args[0]);
    double y1 = Double.parseDouble(args[1]);
    double x2 = Double.parseDouble(args[2]);
    double y2 = Double.parseDouble(args[3]);
    double r = 6371.0; 

    double x = (x2 - x1) / 2;
    double y = (y2 - y1) / 2; // the mean radius of the Earth(in kilometers).
    
    double distance = 2 * r * Math.asin(
      Math.sqrt(
        Math.pow(Math.sin(Math.toRadians(x)), 2) + 
        Math.cos(Math.toRadians(x1)) * Math.cos(Math.toRadians(x2)) * 
        Math.pow(Math.sin(Math.toRadians(y)), 2)
      )
    );

    System.out.println(distance + " kilometers");
  }
}
