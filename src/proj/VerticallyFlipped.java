package proj;

import lab.polymorphism.TextBlock;

/**
 * A text block that is vertically flipped.
 * 
 * @author Wenfei Lin
 */
public class VerticallyFlipped implements TextBlock{
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The textblock to be vertically flipped in the composition.
   */
  TextBlock textBlock;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block by vertically flipping the given block.
   */
  public VerticallyFlipped(TextBlock _textBlock) {
    this.textBlock = _textBlock;
  } // VerticallyFlipped(TextBlock, int)

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
    int lastRow = height - 1;

    // Sanity check
    if ((i < 0) || (i >= height)) {
      throw new Exception("Invalid row " + i);
    } // if the row is invalid

    String result;
    // To vertically flip, reverse the order of the rows being returned
    result = this.textBlock.row(lastRow - i);
    
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
    return this.textBlock.width();
  } // width()

  /**
   * Get the text block used to make the vertically-flipped block.
   */
  public TextBlock[] getContents() {
    // Only input the textBlock used to make the vertically flipped block in the array
    TextBlock[] contents = new TextBlock[] {textBlock};
    return contents;
  } // getContents()
  
  /**
   * Compare this vertically-flipped block to another text block, other, 
   * and determine if they were built the same way.
   */
  public boolean eqv(TextBlock other) {
    boolean equality;

    if (other instanceof VerticallyFlipped) { // If other is also a VerticallyFlipped TextBlock,
      //proceed to further comparison (comparing the contents of each)
      equality = this.textBlock.eqv(other.getContents()[0]);
    } else {
      // Otherwise, only this text block is a VerticallyFlipped TextBlock, 
      // so they are not built the same way
      equality = false;
    }
    return equality;
  } // eqv(TextBlock)
} // class VerticallyFlipped
