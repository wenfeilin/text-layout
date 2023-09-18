package proj;

import lab.polymorphism.*;

public class Centered implements TextBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The textblock to be centered in the composition.
   */
  TextBlock textBlock;

  /**
   * The width that the composition will be centered in.
   */
  int width;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block by centering the given textblock in the given width.
   */
  public Centered(TextBlock _textBlock, int _width) {
    this.textBlock = _textBlock;
    this.width = _width;
  } // Centered(TextBlock, int)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   * 
   * @pre 0 <= i < this.height()
   * @exception Exception if the precondition is not met
   */
  public String row(int i) throws Exception {
    int height = this.textBlock.height();
    int newWidth = this.width;
    int oldWidth = this.textBlock.width();
    int totalPadding = newWidth - oldWidth;

    String padLeft = "";
    String padRight = "";
    if (totalPadding % 2 == 0) {
      padLeft = TBUtils.spaces(totalPadding / 2);
      padRight = TBUtils.spaces(totalPadding / 2);
    } else {
      padLeft = TBUtils.spaces(totalPadding / 2 + 1);
      padRight = TBUtils.spaces(totalPadding / 2);
    }

    // Sanity check
    if ((i < 0) || (i >= height)) {
      throw new Exception("Invalid row " + i);
    } // if the row is invalid

    // have to check that newWidth is not less than textblock's width
    // and if newWidth is negative
    String result;
    if (newWidth < oldWidth) {
      result = this.textBlock.row(i);
    } else {
      result = padLeft.concat(this.textBlock.row(i)).concat(padRight);
    }

    return result;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   */
  public int height() {
    return this.textBlock.height();
  } // height()

  /**
   * Determine how many columns are in the block.
   */
  public int width() {
    return this.width;
  } // width()
} // class Centered
