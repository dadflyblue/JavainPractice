import java.util.Arrays;

public class BinarySearch {

  // from:
  // https://www.coursera.org/learn/cs-algorithms-theory-machines/supplement/i3ok4/optional-enrichment-on-sorting-and-searching

  // 4.2.2 Develop a nonrecursive implementation of binary search.
  public static int binary(int[] arr, int k) {
    if (arr == null) {
      throw new NullPointerException();
    }

    int hi = arr.length;
    int low = 0;
    while (hi > low) {
      int mid = low + (hi - low) / 2;
      if (arr[mid] > k) {
        low = mid + 1;
      } else if (arr[mid] < k) {
        hi = mid;
      } else {
        return mid;
      }
    }
    return -1;
  }

  private static int rbinary(int[] arr, int k, int low, int hi) {
    if (hi <= low) {
      return -1;
    }

    int mid = low + (hi - low) / 2;
    if (arr[mid] > k) {
      return rbinary(arr, k, mid+1, hi);
    } else if (arr[mid] > k) {
      return rbinary(arr, k, low, hi);
    } else {
      return mid;
    }
  }

  public static int rbinary(int[] arr, int k) {
    if (arr == null) {
      throw new NullPointerException();
    }

    return rbinary(arr, k, 0, arr.length);
  }

  public static void main(String[] args) {
    int[] arr = { 1, 2, 3, 5, 6, 6, 7, 8, 9 };
    System.out.println("arr = " + Arrays.toString(arr));

    System.out.println("6 is at: " + rbinary(arr, 6));
    System.out.println("6 is at: " + binary(arr, 6));
  }

}
