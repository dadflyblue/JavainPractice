public class AnimatedHtree {
  
  public static void drawHtree(int n, double size, double x, double y) {
    if (n == 0) 
      return;

    double x0 = x - size/2, x1 = x + size/2;
    double y0 = y - size/2, y1 = y + size/2;
    
    StdDraw.line(x0, y, x1, y);
    StdDraw.line(x0, y0, x0, y1);
    StdDraw.line(x1, y0, x1, y1);

    StdDraw.show();
    StdDraw.pause(100);

    drawHtree(n-1, size/2, x0, y0);
    drawHtree(n-1, size/2, x1, y1);
    drawHtree(n-1, size/2, x0, y1);
    drawHtree(n-1, size/2, x1, y0);
  }

  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);

    StdDraw.enableDoubleBuffering();
    StdDraw.setPenRadius(0.001);
    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);

    drawHtree(n, 0.5, 0.5, 0.5);
  }
}
