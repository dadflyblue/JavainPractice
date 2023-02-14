import java.awt.Color;

public class Luminance {
  
  public static double lum(Color c) {
    int r = c.getRed();
    int g = c.getGreen();
    int b = c.getBlue();
    return .299*r + .587*g + .114*b;
  }

  public static Color toGray(Color c) {
    int y = (int) Math.round(lum(c));
    return new Color(y, y, y);
  }
}
