import java.awt.Color;

public class KernelFilter {

  // Specification:
  // https://coursera.cs.princeton.edu/introcs/assignments/oop1/specification.php

  /*
   * Kernel filter. 
   * Write an image-processing library KernelFilter.java that applies 
   * various kernel filters to images, such as Gaussian blur, sharpen, 
   * Laplacian, emboss, and motion blur. A kernel filter modifies the 
   * pixels in an image by replacing each pixel with a linear combination 
   * of its neighboring pixels. The matrix that characterizes the linear 
   * combination is known as the kernel.
   * 
   * More specifically, to apply a kernel filter to a grayscale image, 
   * perform the following operation for each pixel p:
   * 
      Align the center of the kernel on pixel p.
      The new grayscale value of pixel p is obtained 
      by multiplying each kernel element with the corresponding grayscale value, and adding the results.
   */
  
  // Returns a new picture that applies the identity filter to the given picture.
  public static Picture identity(Picture picture) {
    double[][] kernel = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
    return kernelFilter(picture, kernel);
  }

  // Returns a new picture that applies a Gaussian blur filter to the given picture.
  public static Picture gaussian(Picture picture) {
    double[][] kernel = {{1.0/16, 2.0/16, 1.0/16}, {2.0/16, 4.0/16, 2.0/16}, {1.0/16, 2.0/16, 1.0/16}};
    return kernelFilter(picture, kernel);
  }

  // Returns a new picture that applies a sharpen filter to the given picture.
  public static Picture sharpen(Picture picture) {
    double[][] kernel = {{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};
    return kernelFilter(picture, kernel);
  }

  // Returns a new picture that applies an Laplacian filter to the given picture.
  public static Picture laplacian(Picture picture) {
    double[][] kernel = {{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};
    return kernelFilter(picture, kernel);
  }

  // Returns a new picture that applies an emboss filter to the given picture.
  public static Picture emboss(Picture picture) {
    double[][] kernel = {{-2, -1, 0}, {-1, 1, 1}, {0, 1, 2}};
    return kernelFilter(picture, kernel);
  }

  // Returns a new picture that applies a motion blur filter to the given picture.
  public static Picture motionBlur(Picture picture) {
    double[][] kernel = {
      {1.0/9, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 1.0/9, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 1.0/9, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 1.0/9, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 1.0/9, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 1.0/9, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 1.0/9, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 1.0/9, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 1.0/9},
    };
    return kernelFilter(picture, kernel);
  }

  private static Picture kernelFilter(Picture picture, double[][] kernel) {
    Picture targePicture = new Picture(picture.width(), picture.height());

    for (int col = 0; col < picture.width(); col++) {
      for (int row = 0; row < picture.height(); row++) {
        double redSum = 0;
        double greenSum = 0;
        double blueSum = 0;

        for (int i = 0; i < kernel.length; i++) {
          for (int j = 0; j < kernel.length; j++) {
            int newCol = (col - kernel.length / 2 + j + picture.width()) % picture.width();
            int newRow = (row - kernel.length / 2 + i + picture.height()) % picture.height();

            Color color = picture.get(newCol, newRow);
            redSum += color.getRed() * kernel[i][j];
            greenSum += color.getGreen() * kernel[i][j];
            blueSum += color.getBlue() * kernel[i][j];
          }
        }

        targePicture.set(col, row, 
          new Color(
              clamping(Math.round(redSum)), 
              clamping(Math.round(greenSum)),
              clamping(Math.round(blueSum))
            )
        );
      }
    }
    return targePicture;
  }

  private static int clamping(long value) {
    if (value < 0) {
      return 0;
    } else if (value > 255) {
      return 255;
    } else {
      return (int) value;
    }
  }

  // Test client (ungraded).
  public static void main(String[] args) {
    String file = args[0];
    Picture p = new Picture(file);
    p.show();
    Picture ide = identity(p);
    ide.show();
    Picture gau = gaussian(p);
    gau.show();
    Picture sha = sharpen(p);
    sha.show();
    Picture lap = laplacian(p);
    lap.show();
    Picture emb = emboss(p);
    emb.show();
    Picture mot = motionBlur(p);
    mot.show();
  }
}
