import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Clock {

  // specification:
  // https://coursera.cs.princeton.edu/introcs/assignments/oop2/specification.php

  private int hours;
  private int minutes;

  // Creates a clock whose initial time is h hours and m minutes.
  public Clock(int h, int m) {
    init(h, m);
  }

  private static final Pattern FORM_PATTERN = Pattern.compile("(\\d\\d):(\\d\\d)");

  // Creates a clock whose initial time is specified as a string, using the format HH:MM.
  public Clock(String s) {
    if (s == null) {
      throw new NullPointerException();
    }

    Matcher matcher = FORM_PATTERN.matcher(s);
    if (!matcher.matches()) {
      throw new IllegalArgumentException();
    }

    int h = Integer.parseInt(matcher.group(1));
    int m = Integer.parseInt(matcher.group(2));
    init(h, m);
  }

  private void init(int h, int m) {
    if (h < 0 || h > 23) {
      throw new IllegalArgumentException();
    }
    if (m < 0 || m > 59) {
      throw new IllegalArgumentException();
    }
    hours = h;
    minutes = m;
  }

  // Returns a string representation of this clock, using the format HH:MM.
  public String toString() {
    return String.format("%02d:%02d", hours, minutes);
  }

  // Is the time on this clock earlier than the time on that one?
  public boolean isEarlierThan(Clock that) {
    if (this.hours < that.hours) {
      return true;
    } else if (this.hours == that.hours) {
      return this.minutes < that.minutes;
    } else {
      return false;
    }
  }

  // Adds 1 minute to the time on this clock.
  public void tic() {
    toc(1);
  }

  // Adds Δ minutes to the time on this clock.
  public void toc(int delta) {
    if (delta < 0) {
      throw new IllegalArgumentException();
    }

    minutes = minutes + delta;
    hours += minutes / 60;
    minutes = minutes % 60;
    hours = hours % 24;
  }

  // Test client (see below).
  public static void main(String[] args) {
    Clock c1 = new Clock("06:00");
    System.out.println("c1: " + c1);
    Clock c2 = new Clock("23:59");
    System.out.println("c2: " + c2);
    System.out.println("c1 is earlier than c2? " + c1.isEarlierThan(c2));
    
    c1.tic();
    System.out.println("c1 tic: " + c1);
    c2.tic();
    System.out.println("c2 tic: " + c2);
    c1.toc(60*24);
    System.out.println("c1 toc 24*60: " + c1);
  }
}
