public class LCS {
  // From Longest common subsequence problem: https://introcs.cs.princeton.edu/java/23recursion/
  // Longest common subsequence problem. Given two strings x and y, 
  // we wish to compute their (LCS). If we delete some characters from x and some characters from y,
  // and the resulting two strings are equal, we call the resulting string a common subsequence. 
  // The LCS problem is to find a common subsequence of two strings that is as long as possible. 
  // For example, the LCS of GGCACCACG and ACGGCGGATACG is GGCAACG, a string of length 7.
  public static String lcs(String s, String t) {
    int m = s.length(), n = t.length();
    int[][] opt = new int[m+1][n+1];

    for (int i = m-1; i >= 0; i--) {
      for (int j = n-1; j >= 0; j--) {
        if (s.charAt(i) == t.charAt(j)) {
          opt[i][j] = opt[i+1][j+1] + 1;
        } else {
          opt[i][j] = Math.max(opt[i][j+1], opt[i+1][j]);
        }
      }
    }

    String lcs = "";
    int i = 0, j = 0;
    while (i < m && j < n) {
      if (s.charAt(i) == t.charAt(j)) {
        lcs += s.charAt(i);
        i++;
        j++;
      } else if (opt[i+1][j] >= opt[i][j+1]) {
        i++;
      } else {
        j++;
      }
    }
    return lcs;
  }

  public static void main(String[] args) {
    StdOut.println(lcs(args[0], args[1]));
  }
}
