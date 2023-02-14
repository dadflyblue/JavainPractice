public class AudioCollage {

  // specification: https://coursera.cs.princeton.edu/introcs/assignments/functions/specification.php

  // Returns a new array that rescales a[] by a multiplicative factor of alpha.
  public static double[] amplify(double[] a, double alpha) {
    double[] b = new double[a.length];
    
    for (int i = 0; i < b.length; i++) {
      b[i] = a[i] * alpha;
    }
    return b;
  }

  // Returns a new array that is the reverse of a[].
  public static double[] reverse(double[] a) {
    double[] b = new double[a.length];

    for (int h = 0; h < b.length; h++) {
      int r = b.length - 1 - h;
      b[h] = a[r];
    }
    return b;
  }

  // Returns a new array that is the concatenation of a[] and b[].
  public static double[] merge(double[] a, double[] b) {
    double[] m = new double[a.length + b.length];
    
    int i = 0;
    for (; i < a.length; i++) {
      m[i] = a[i];
    }
    for (int j = 0; i < m.length; i++, j++) {
      m[i] = b[j];
    }
    return m;
  }

  // Returns a new array that is the sum of a[] and b[],
  // padding the shorter arrays with trailing 0s if necessary.
  public static double[] mix(double[] a, double[] b) {
    int l = Math.max(a.length, b.length);
    double[] m = new double[l];

    for (int i = 0; i < m.length; i++) {
      double x = (i < a.length) ? a[i] : 0;
      double y = (i < b.length) ? b[i] : 0;
      m[i] = x + y;
    }
    return m;
  }

  // Returns a new array that changes the speed by the given factor.
  public static double[] changeSpeed(double[] a, double alpha) {
    int l = (int) Math.floor(a.length / alpha);
    double[] m = new double[l];
    for (int i = 0; i < m.length; i++) {
      int p = (int) Math.floor(i * alpha);
      m[i] = a[p];
    }
    return m;
  }

  // Creates an audio collage and plays it on standard audio.
  // See below for the requirements.
  public static void main(String[] args) {
    String[] res = {
      "CS-Princeton/src/main/resources/beatbox.wav",
      "CS-Princeton/src/main/resources/exposure.wav",
      "CS-Princeton/src/main/resources/chimes.wav",
      "CS-Princeton/src/main/resources/cow.wav",
      "CS-Princeton/src/main/resources/dialup.wav"
    };
    
    double[] b = StdAudio.read("CS-Princeton/src/main/resources/buzzer.wav");

    for (String file : res) {
      double[] a = StdAudio.read(file);
      StdOut.println("play: " + file);
      StdAudio.play(a);

      double[] m = amplify(a, 2.0);
      StdOut.println("amplify(2): " + file);
      StdAudio.play(m);

      m = reverse(a);
      StdOut.println("reverse: " + file);
      StdAudio.play(m);

      m = merge(a, b);
      StdOut.println("merge(buzzer): " + file);
      StdAudio.play(m);

      m = mix(a, b);
      StdOut.println("mix(buzzer): " + file);
      StdAudio.play(m);

      m = changeSpeed(a, 2);
      StdOut.println("changeSpeed(2): " + file);
      StdAudio.play(m);
    }
  }
}
