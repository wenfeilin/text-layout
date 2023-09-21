package lab.polymorphism;


import java.io.PrintWriter;

/**
 * Utilities for TextBlocks.
 * 
 * @author Samuel A. Rebelsky
 * @version 1.3 of September 2014
 */
public class TBUtils {
  // +--------------+------------------------------------------------------
  // | Class Fields |
  // +--------------+

  /**
   * A really big sequence of dashes. This sequence may grow as the program operates.
   */
  static String lotsOfDashes = "--";

  /**
   * A really big sequence of spaces. This sequence may grow as the program operates.
   */
  static String lotsOfSpaces = "  ";

  // +----------------+----------------------------------------------------
  // | Static Methods |
  // +----------------+

  /**
   * Build a sequence of dashes of a specified length.
   */
  static String dashes(int len) {
    // Note: This strategy probably represents an overkill in
    // attempts at efficiency.
    // Make sure the collection of dashes is big enough
    while (lotsOfDashes.length() < len) {
      lotsOfDashes = lotsOfDashes.concat(lotsOfDashes);
    } // while
    // Extract an appropriate length substring
    return lotsOfDashes.substring(0, len);
  } // dashes(int)

  /**
   * Print a TextBlock to the specified destination.
   */
  public static void print(PrintWriter pen, TextBlock block) {
    for (int i = 0; i < block.height(); i++) {
      // Even though we only call block.row with a valid i,
      // we need to put the call in a try/catch block.
      try {
        pen.println(block.row(i));
      } catch (Exception e) {
        pen.println();
      } // catch (Exception)
    } // for
  } // print(PrintWriter, TextBlock)

  /**
   * Build a sequence of spaces of a specified length.
   */
  public static String spaces(int len) {
    // As with dashes, this is probably overkill.
    // Make sure the collection of dashes is big enough
    while (lotsOfSpaces.length() < len) {
      lotsOfSpaces = lotsOfSpaces.concat(lotsOfSpaces);
    } // while
    // Extract an appropriate length substring
    return lotsOfSpaces.substring(0, len);
  } // spaces(int)

  public static boolean equal(TextBlock t1, TextBlock t2) {
    PrintWriter pen = new PrintWriter(System.out, true);

    int t1Height = t1.height();
    int t2Height = t2.height();
    int t1Width = t1.width();
    int t2Width = t2.width();

    if (!(t1Height == t2Height)) {
      return false;
    } else if (!(t1Width == t2Width)) {
      return false;
    } else {
      for (int i = 0; i < t1Height; i++) {
        try {
          if (!(t1.row(i).equals(t2.row(i)))) {
            return false;
          }
        } catch (Exception e) { // what is the possible exception to catch here?
          pen.println();
        } // catch (Exception)
      }
      return true;
    }

  } // equal(TextBlock t1, TextBlock t2)

  public static boolean eqv(TextBlock t1, TextBlock t2) {
     return t1.eqv(t2);

  } // eqv(TextBlock t1, TextBlock t2)

  public static boolean eq(TextBlock t1, TextBlock t2) {
    return t1 == t2;
  } // eq(TextBlock t1, TextBlock t2)

  public static String toString(TextBlock textBlock) throws Exception {
    int height = textBlock.height();
    String textBlockString = "";
 
    for (int i = 0; i < height - 1; i++) {
      textBlockString += textBlock.row(i) + "\n";
    }
    textBlockString += textBlock.row(height - 1);
    return textBlockString;
  } // toString(TextBlock)

} // class TBUtils
