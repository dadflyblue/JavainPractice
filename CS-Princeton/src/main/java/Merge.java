public class Merge {

  public static void sort(String[] ss) {
    if (ss == null) {
      throw new NullPointerException();
    }

    aux = new String[ss.length];
    sort(ss, 0, ss.length);
  }

  private static void sort(String[] a, int low, int hi) {
    int d = hi - low;
    if (d <= 1) {
      return;
    }

    int mid = low + d / 2;
    sort(a, low, mid);
    sort(a, mid, hi);
    merge(a, low, mid, hi);
  }

  private static void merge(String[] a, int low, int mid, int hi) {
    int i = low, j = mid;
    for (int k = low; k < hi; k++) {
      if (i == mid) {
        aux[k] = a[j++];
      } else if (j == hi) {
        aux[k] = a[i++];
      } else if (a[i].compareTo(a[j]) <= 0) {
        aux[k] = a[i++];
      } else {
        aux[k] = a[j++];
      }
    }

    // copy aux back to a
    for (int k = low; k < hi; k++) {
      a[k] = aux[k];
    }
  }

  private static String[] aux;

  public static void main(String[] args) {
    String[] ss = Generator.randomStrings(100, 5, "abcdefghijklmnopqrstuvwxyz");
    sort(ss);
    for (int i = 0; i < ss.length; i++) {
      System.out.println(ss[i]);
    }
  }

}
