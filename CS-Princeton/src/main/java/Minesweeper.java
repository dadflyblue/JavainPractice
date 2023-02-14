public class Minesweeper {

  // specification: https://coursera.cs.princeton.edu/introcs/assignments/arrays/specification.php
  public static void main(String[] args) {
    int m = Integer.parseInt(args[0]);
    int n = Integer.parseInt(args[1]);
    int k = Integer.parseInt(args[2]);

    int[][] a = new int[m][n];
    
    // Generates k mines in the m*n cells.
    // For each mine r locating in cell a[x][y], 
    // use r/m as x and r%m as y.
    for (int i = 0; i < k; i++) {
      int r = (int) (Math.random() * m * n);
      int x = r / n;
      int y = r % n;

      // this cell has already been a mine.
      if (a[x][y] == -1) {
        i--;
        continue;
      }

      a[x][y] = -1; // use -1 to represent mine(*)
    }

    // fill out numbers in cells
    for (int x = 0; x < m; x++) {
      for (int y = 0; y < n; y++) {
        if (a[x][y] != -1)
          continue;

        if (x > 0 && y > 0) {
          if (a[x-1][y-1] != -1) a[x-1][y-1] += 1;
        }

        if (x > 0) {
          if (a[x-1][y] != -1) a[x-1][y] += 1;
        }
  
        if (x > 0 && y < n-1) {
          if (a[x-1][y+1] != -1) a[x-1][y+1] += 1;
        }
  
        if (y > 0) {
          if (a[x][y-1] != -1) a[x][y-1] += 1;
        }
  
        if (y < n-1) {
          if (a[x][y+1] != -1) a[x][y+1] += 1;
        }
        
        if (x < m-1) {
          if (a[x+1][y] != -1) a[x+1][y] += 1;
        }
        
        if (x < m-1 && y < n-1) {
          if (a[x+1][y+1] != -1) a[x+1][y+1] += 1;
        }
  
        if (x < m-1 && y > 0) {
          if (a[x+1][y-1] != -1) a[x+1][y-1] += 1;
        }
      }
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (a[i][j] == -1) {
          System.out.print("*  ");
        } else {
          System.out.print(a[i][j] + "  ");
        }
      }
      System.out.println();
    }
  }
}
