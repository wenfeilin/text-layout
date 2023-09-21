package lab.polymorphism;

import proj.SmileyBlock;

/**
 * The horizontal composition of two text blocks.
 * 
 * @author Samuel A. Rebelsky
 * @version 1.3 of February 2019
 */
public class HComposition implements TextBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The left portion of the composition.
   */
  TextBlock left;

  /**
   * The right portion of the composition.
   */
  TextBlock right;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block by composing left and right side by side.
   */
  public HComposition(TextBlock left, TextBlock right) {
    this.left = left;
    this.right = right;
  } // HComposition(TextBlock, TextBlock)

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
    int lh = this.left.height();
    int rh = this.right.height();
    int h = Math.max(lh, rh);

    // Sanity check
    if ((i < 0) || (i >= h)) {
      throw new Exception("Invalid row " + i);
    } // if the row is invalid

    String result;
    if (i < lh) {
      result = this.left.row(i);
    } else {
      result = TBUtils.spaces(this.left.width());
    }
    if (i < rh) {
      result = result.concat(this.right.row(i));
    } else {
      result = result.concat(TBUtils.spaces(this.right.width()));
    }

    return result;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   */
  public int height() {
    // The height is the greater of the heights of the left and right
    // blocks.
    return Math.max(this.left.height(), this.right.height());
  } // height()

  /**
   * Determine how many columns are in the block.
   */
  public int width() {
    // The number of columns is the width of the left plus the
    // width of the right.
    return this.left.width() + this.right.width();
  } // width()


  // PLACEHOLDERS
  public TextBlock[] getContents() {
    TextBlock[] contents = new TextBlock[] {left, right};
    return contents;
  }
  
  public boolean eqv(TextBlock other) {
    boolean equalityLeftPart;
    boolean equalityRightPart;
    boolean combinedEquality;

    if (other instanceof SmileyBlock) { // if both are Truncated TextBlocks,
      //proceed further comparison
      equalityLeftPart = this.left.eqv(other.getContents()[0]);
      equalityRightPart = this.right.eqv(other.getContents()[1]);
      combinedEquality = equalityLeftPart && equalityRightPart;
    } else {
      combinedEquality = false;
    }
    return combinedEquality;
  } // eqv(TextBlock)

} // class HComposition
