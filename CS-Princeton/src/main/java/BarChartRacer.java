import java.util.Arrays;

public class BarChartRacer {

  // Spec:
  // https://coursera.cs.princeton.edu/introcs/assignments/barchart/specification.php

  public static void main(String[] args) {
    String file = args[0];
    int k = Integer.parseInt(args[1]);

    StdDraw.setCanvasSize(1000, 700);
    StdDraw.enableDoubleBuffering();

    In in = new In(file);
    try {
      String title = in.readLine();
      String xAxis = in.readLine();
      String source = in.readLine();
      BarChart chart = new BarChart(title, xAxis, source);

      while (in.hasNextLine()) {
        in.readLine(); // there is an empty line here.
        int n = Integer.parseInt(in.readLine());
        Bar[] bars = new Bar[n];
        String caption = null;
        for (int i = 0; i < n; i++) {
          String[] s = in.readLine().split(",");
          Bar bar = new Bar(s[1], Integer.parseInt(s[3]), s[4]);
          bars[i] = bar;
          if (caption == null) {
            caption = s[0];
          }
        }

        Arrays.sort(bars);
        k = Math.min(k, n);

        chart.reset();
        chart.setCaption(caption);
        for (int i = n-1; i >= n-k; i--) {
          chart.add(bars[i].getName(), bars[i].getValue(), bars[i].getCategory());
        }

        StdDraw.clear();
        chart.draw();
        StdDraw.show();
        StdDraw.pause(20);
      }
    } finally {
      in.close();
    }
  }
}
