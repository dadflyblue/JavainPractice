public class SubStrings {

  // Question 3.1.22 Write a program that takes a start string and 
  // a stop string as command-line arguments and prints all substrings
  // of a given string that start with the first, end with the second, 
  // and otherwise contain neither. Note: Be especially careful of overlaps!

  // From: https://www.coursera.org/learn/cs-programming-java/supplement/hfVns/optional-enrichment-on-abstract-data-types

  public static void main(String[] args) {
    String start = args[0];
    String end = args[1];
    String str = "dxxxstart 1111 end, sffeef start end xxx start 2endxx wwstartend";

    int d = 0;
    int i = 0;
    while (i != -1 && d != -1) {
      i = str.indexOf(start, d);
      if (i != -1) {
        d = str.indexOf(end, i);
        if (d != -1) {
          // append the postfix to the substring
          d += end.length();
          System.out.println(str.substring(i, d));
        }
      }
    }
  }
}
