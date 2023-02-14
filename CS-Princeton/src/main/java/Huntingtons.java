public class Huntingtons {
  
  // Specification:
  // https://coursera.cs.princeton.edu/introcs/assignments/oop1/specification.php

  /*
   * Huntington’s disease detector. 
   * Huntington’s disease is an inherited and fatal neurological disorder. 
   * Although there is currently no cure, in 1993 scientists discovered a very accurate genetic test. 
   * The gene that causes Huntington’s disease is located on chromosome 4 and has a variable number of 
   * (consecutive) repeats of the CAG trinucleotide. The normal range of CAG repeats is between 10 and 35.
   * Individuals with Huntington’s disease have between 36 and 180 repeats. Doctors can use a PCR-based DNA test; 
   * count the maximum number of repeats; and use the following table to generate a diagnosis:
   * 
   * repeats diagnosis
   * 0–9 not human
   * 10–35 normal
   * 36–39 high risk
   * 40–180 Huntington’s  
   * 181– not human
   *
   */

  // Returns the maximum number of consecutive repeats of CAG in the DNA string.
  public static int maxRepeats(String dna) {
    int c = 0;
    int i = 0;
    int max = c;
    while (i <= dna.length() - 3) {
      if (dna.substring(i, i + 3).equals("CAG")) {
        c++;
        i += 3;
        max = Math.max(c, max);
      } else {
        i++;
        c = 0;
      }
    }
    return max;
  }

  // Returns a copy of s, with all whitespace (spaces, tabs, and newlines) removed.
  public static String removeWhitespace(String s) {
    return s.replace(" ", "")
            .replace("\t", "")
            .replace("\n", "");
  }

  // Returns one of these diagnoses corresponding to the maximum number of repeats:
  // "not human", "normal", "high risk", or "Huntington's".
  public static String diagnose(int maxRepeats) {
    if (maxRepeats <= 9 || maxRepeats >= 181) {
      return "not human";
    } else if (10 <= maxRepeats && maxRepeats <= 35) {
      return "normal";
    } else if (36 <= maxRepeats && maxRepeats <= 39) {
      return "high risk";
    } else {
      return "Huntington's";
    }
  }

  // Sample client (see below).
  public static void main(String[] args) {
    String file = args[0];

    In in = new In(file);
    try {
      String gens = in.readAll();
      gens = removeWhitespace(gens);
      int c = maxRepeats(gens);
      System.out.println("max repeats = " + c);
      System.out.println(diagnose(c));
    } finally {
      in.close();
    }
  }
}
