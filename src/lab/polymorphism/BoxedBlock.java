package lab.polymorphism;

/**
 * A text block surrounded by a box.
 *
 * @author Samuel A. Rebelsky
 * @author Wenfei Lin
 */
public class BoxedBlock implements TextBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The stuff in the box.
   */
  TextBlock contents;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new line with contents _contents.
   */
  public BoxedBlock(TextBlock _contents) {
    this.contents = _contents;
  } // BoxedBlock(String)

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
    int h = this.contents.height();
    // The top and bottom of the box
    if ((i == 0) || (i == h + 1)) {
      return "+" + TBUtils.dashes(this.contents.width()) + "+";
    }
    // Stuff within the box
    else if ((i > 0) && (i <= h)) {
      return "|" + this.contents.row(i - 1) + "|";
    }
    // Everything else
    else {
      throw new Exception("Invalid row " + i);
    }
  } // row(int)

  /**
   * Determine how many rows are in the block.
   */
  public int height() {
    return 2 + this.contents.height();
  } // height()

  /**
   * Determine how many columns are in the block.
   */
  public int width() {
    return 2 + this.contents.width();
  } // width()

  /**
   * Get the text block used to make the boxed block.
   */
  public TextBlock[] getContents() {
    // Only input the textBlock used to make the boxed block in the array
    TextBlock[] innerContents = new TextBlock[] {contents};
    return innerContents;
  } // getContents()
  
  /**
   * Compare this boxed block to another text block, other, 
   * and determine if they were built the same way
   */
  public boolean eqv(TextBlock other) {
    boolean equality;

    if (other instanceof BoxedBlock) { // If other is also a BoxedBlock,
      // proceed to further comparison (comparing the contents of each)
      equality = this.contents.eqv(other.getContents()[0]);
    } else {
      // Otherwise, only this text block is a BoxedBlock, 
      // so they are not built the same way
      equality = false;
    }
    return equality;
  } // eqv(TextBlock)
} // class BoxedBlock
