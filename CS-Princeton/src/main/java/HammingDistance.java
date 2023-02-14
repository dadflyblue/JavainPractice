public class HammingDistance {

  /**
   * <p>
   * Source:
   *  https://www.coursera.org/learn/cs-programming-java/supplement/yvKrY/optional-enrichment-on-recursion
   * </p>
   * 
   * 2.3.21 Hamming distance. The Hamming distance between two bit strings of 
   * length n is equal to the number of bits in which the two strings differ. 
   * Write a program that reads in an integer k and a bit string s from the command line, 
   * and prints all bit strings that have Hamming distance at most k from s. 
   * For example, if k is 2 and s is 0000, then your program should print
   * 
   * @param ps used to record possible combination positions in s
   * @param cur used to record current combination cursor point in s
   * @param next used to record next try char position in s
   * @param s the all element string used to operate
   * @param k used to track k element of combination
   */
  public static void flipcomb(int[] ps, int cur, int next, String s, int k) {
    if (k == 0) {
      char[] a = s.toCharArray();
      for (int i = 0; i < ps.length; i++) {
        char c = a[ps[i]];
        a[ps[i]] = c == '0' ? '1' : '0';
      }
      System.out.println(new String(a));
    } else {
      for (int i = next; i < s.length(); i++) {
        ps[cur] = i;
        flipcomb(ps, cur+1, i+1, s, k-1);
      }
    }
  }
  
  public static void main(String[] args) {
    int k = Integer.parseInt(args[0]);
    String s = args[1];

    int[] p = new int[k]; // hold the positions of combination
    flipcomb(p, 0, 0, s, k);
  }
}
