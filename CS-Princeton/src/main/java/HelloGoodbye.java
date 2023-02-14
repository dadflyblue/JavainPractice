public class HelloGoodbye {

  // specification: https://coursera.cs.princeton.edu/introcs/assignments/hello/specification.php
  // faq: https://coursera.cs.princeton.edu/introcs/assignments/hello/faq.php

  public static void main(String[] args) {
    System.out.println("Hello " + args[0] + " and " + args[1] + ".");
    System.out.println("Goodbye " + args[1] + " and " + args[0] + ".");
  }
}
