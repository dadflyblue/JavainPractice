import java.awt.Color;

public class StatsGrayscale {

  // Question 3.1.4 Write a program that takes the name of a grayscale image
  // file as a command-line argument and uses StdDraw to plot a histogram of 
  // the frequency of occurrence of each of the 256 grayscale intensities.

  // From: https://www.coursera.org/learn/cs-programming-java/supplement/hfVns/optional-enrichment-on-abstract-data-types

  public static void main(String[] args) {
    String file = args[0];
    Picture p = new Picture(file);
    int[] freq = new int[256];
    
    int max = 0;
    for (int col = 0; col < p.width(); col++) {
      for (int row = 0; row < p.height(); row++) {
        int gray = p.get(col, row).getRed();
        freq[gray]++;
        if (freq[gray] > max) {
          max = freq[gray];
        }
      }
    }

    StdDraw.setXscale(-1, freq.length);
    StdDraw.setYscale(0, max);
    for (int i = 0; i < freq.length; i++) {
      StdDraw.setPenColor(new Color(i, i, i));
      StdDraw.filledRectangle(i, freq[i]/2.0, 0.25, freq[i]/2.0);
    }
  }
}
