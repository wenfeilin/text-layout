package lab.polymorphism;

/**
 * The horizontal composition of two text blocks.
 * 
 * @author Samuel A. Rebelsky
 * @author Wenfei Lin
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


  /**
   * Get the text blocks used to make the horizontally-composed block
   */
  public TextBlock[] getContents() {
    // Input the textBlocks used to make the horizontally-composed block in the array
    TextBlock[] contents = new TextBlock[] {left, right};
    return contents;
  } // getContents()
  
  /**
   * Compare this horizontally-composed block to another text block, other, 
   * and determine if they were built the same way
   */
  public boolean eqv(TextBlock other) {
    boolean equalityLeftPart;
    boolean equalityRightPart;
    boolean combinedEquality;

    if (other instanceof HComposition) { // If other is also a HComposition TextBlock,
      // proceed to further comparison (comparing the contents of each: left part
      // to left part and right part to right part)
      equalityLeftPart = this.left.eqv(other.getContents()[0]);
      equalityRightPart = this.right.eqv(other.getContents()[1]);
      combinedEquality = equalityLeftPart && equalityRightPart;
    } else {
      // Otherwise, only this text block is a HComposition TextBlock, 
      // so they are not built the same way
      combinedEquality = false;
    }
    return combinedEquality;
  } // eqv(TextBlock)

} // class HComposition
