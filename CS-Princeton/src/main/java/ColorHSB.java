public class ColorHSB {

  // specification:
  // https://coursera.cs.princeton.edu/introcs/assignments/oop2/specification.php

  private final int hue;
  private final int saturation;
  private final int brightness;

  // Creates a color with hue h, saturation s, and brightness b.
  public ColorHSB(int h, int s, int b) {
    if (h < 0 || h > 359) {
      throw new IllegalArgumentException("h should be in ragne: [0, 359]");
    }

    if (s < 0 || s > 100) {
      throw new IllegalArgumentException("s should be in range: [0, 100]");
    }

    if (b < 0 || b > 100) {
      throw new IllegalArgumentException("b should be in range: [0, 100]");
    }

    hue = h;
    saturation = s;
    brightness = b;
  }

  // Returns a string representation of this color, using the format (h, s, b).
  public String toString() {
    return String.format("(%d, %d, %d)", hue, saturation, brightness);
  }

  // Is this color a shade of gray?
  public boolean isGrayscale() {
    return (saturation == 0 || brightness == 0);
  }

  // Returns the squared distance between the two colors.
  public int distanceSquaredTo(ColorHSB that) {
    if (that == null) {
      throw new NullPointerException("that cannot be null");
    }

    int h = this.hue - that.hue;
    int ah = 360 - Math.abs(h);
    int s = this.saturation - that.saturation;
    int b = this.brightness - that.brightness;
    return (Math.min(h*h, ah*ah) + s*s + b*b);
  }

  // Sample client (see below).
  public static void main(String[] args) {
    int h = Integer.parseInt(args[0]);
    int s = Integer.parseInt(args[1]);
    int b = Integer.parseInt(args[2]);

    ColorHSB target = new ColorHSB(h, s, b);
    
    int nearestDistance = Integer.MAX_VALUE;
    String nearestName = "";
    ColorHSB nearestHSB = null;
    while (!StdIn.isEmpty()) {
      String n = StdIn.readString();
      ColorHSB hsb = new ColorHSB(StdIn.readInt(), StdIn.readInt(), StdIn.readInt());
      int d = target.distanceSquaredTo(hsb);
      if (d < nearestDistance) {
        nearestDistance = d;
        nearestName = n;
        nearestHSB = hsb;
      }
    }

    System.out.println(nearestName + " " + nearestHSB);
  }

}
