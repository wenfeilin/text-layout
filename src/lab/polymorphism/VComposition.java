package lab.polymorphism;

/**
 * The vertical composition of two text blocks.
 * 
 * @author Samuel A. Rebelsky
 * @author Wenfei Lin
 */
public class VComposition implements TextBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The top portion of the composition.
   */
  TextBlock top;

  /**
   * The bottom portion of the composition.
   */
  TextBlock bottom;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block by composing top and bottom vertically.
   */
  public VComposition(TextBlock top, TextBlock bottom) {
    this.top = top;
    this.bottom = bottom;
  } // VComposition(TextBlock, TextBlock)

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
    // Gather some basic information
    int th = this.top.height();
    int bh = this.bottom.height();
    int tw = this.top.width();
    int bw = this.bottom.width();
    int h = th + bh;

    // Determine padding
    String padtop = "";
    String padbot = "";
    if (tw > bw) {
      padbot = TBUtils.spaces(tw - bw);
    } else {
      padtop = TBUtils.spaces(bw - tw);
    } // the bottom is wider

    if ((i < 0) || (i >= h)) {
      throw new Exception("Invalid row " + i);
    } else if (i < th) {
      return this.top.row(i) + padtop;
    } else {
      return this.bottom.row(i - th) + padbot;
    } // if the row is in the bottom half
  } // row(int)

  /**
   * Determine how many rows are in the block.
   */
  public int height() {
    // The height is the sum of the heights of the top and bottom
    // blocks.
    return this.top.height() + this.bottom.height();
  } // height()

  /**
   * Determine how many columns are in the block.
   */
  public int width() {
    // The width is the greater of the widths of the top and bottom
    // blocks.
    return Math.max(this.top.width(), this.bottom.width());
  } // width()

  /**
   * Get the text blocks used to make the vertically-composed block
   */
  public TextBlock[] getContents() {
    // Input the textBlocks used to make the vertically-composed block in the array
    TextBlock[] contents = new TextBlock[] {top, bottom};
    return contents;
  } // getContents()
  
  /**
   * Compare this vertically-composed block to another text block, other, 
   * and determine if they were built the same way
   */
  public boolean eqv(TextBlock other) {
    boolean equalityTopPart;
    boolean equalityBotPart;
    boolean combinedEquality;

    if (other instanceof VComposition) { // If other is also a VComposition TextBlock,
      // proceed to further comparison (comparing the contents of each: top part
      // to top part and bottom part to bottom part)
      equalityTopPart = this.top.eqv(other.getContents()[0]);
      equalityBotPart = this.bottom.eqv(other.getContents()[1]);
      combinedEquality = equalityTopPart && equalityBotPart;
    } else {
      // Otherwise, only this text block is a VComposition TextBlock, 
      // so they are not built the same way
      combinedEquality = false;
    }
    return combinedEquality;
  } // eqv(TextBlock)

} // class VComposition
