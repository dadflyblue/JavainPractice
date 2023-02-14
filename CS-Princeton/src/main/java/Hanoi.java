public class Hanoi {
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);

    if (n < 0) {
      System.out.println("no move needed!");
      return;
    }

    String ms = hanoi(n, false);
    System.out.println(ms);
    System.out.println("steps: " + c);
  }

  static long c = 0;

  public static String hanoi(int n, boolean left) {
    if (n == 0) 
      return " ";

    String move = "";
    if (left) {
      move = n + "L";
    } else {
      move = n + "R";
    }
    c++;

    return hanoi(n-1, !left) + move + hanoi(n-1, !left);
  }
}
