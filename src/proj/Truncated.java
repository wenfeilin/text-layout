package proj;

import lab.polymorphism.*;

public class Truncated implements TextBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The textblock to be truncated in the composition.
   */
  TextBlock textBlock;

  /**
   * The maximum width, determining truncation in the composition.
   */
  int maxWidth;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block by truncating the given textblock to the maximum width.
   */
  public Truncated(TextBlock _textBlock, int _maxWidth) {
    this.textBlock = _textBlock;
    this.maxWidth = _maxWidth;
  } // Truncated(TextBlock, int)

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
    int newWidth = this.maxWidth;
    int oldWidth = this.textBlock.width();
    int height = this.textBlock.height();

    // Sanity check
    if ((i < 0) || (i >= height)) {
      throw new Exception("Invalid row " + i);
    } // if the row is invalid

    // have to check that newWidth is not greater than textblock's width
    // and if newWidth is negative
    String result;
    if (newWidth > oldWidth) {
      result = this.textBlock.row(i);
    } else {
      result = this.textBlock.row(i).substring(0, newWidth);
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
    return this.maxWidth;
  } // width()
} // class Truncated
