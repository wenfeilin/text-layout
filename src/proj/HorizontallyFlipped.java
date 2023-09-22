package proj;

import lab.polymorphism.TextBlock;

/**
 * A text block that is horizontally flipped.
 * 
 * @author Wenfei Lin
 */
public class HorizontallyFlipped implements TextBlock{
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The textblock to be horizontally flipped in the composition.
   */
  TextBlock textBlock;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block by horizontally flipping the given block.
   */
  public HorizontallyFlipped(TextBlock _textBlock) {
    this.textBlock = _textBlock;
  } // HorizontallyFlipped(TextBlock, int)

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

    // Sanity check
    if ((i < 0) || (i >= height)) {
      throw new Exception("Invalid row " + i);
    } // if the row is invalid

    // Reverse each row by string reversing to horizontally flip
    StringBuffer result = new StringBuffer (this.textBlock.row(i));
    return new String (result.reverse());
  } // row(int)

  /**
   * Determine how many rows are in the block.
   */
  public int height() {
    return this.textBlock.height(); // same
  } // height()

  /**
   * Determine how many columns are in the block.
   */
  public int width() {
    return this.textBlock.width(); // same
  } // width()

  /**
   * Get the text block used to make the horizontally-flipped block.
   */
  public TextBlock[] getContents() {
    // Only input the textBlock used to make the horizontally flipped block in the array
    TextBlock[] contents = new TextBlock[] {textBlock};
    return contents;
  } // getContents()
  
  /**
   * Compare this horizontally-flipped block to another text block, other, 
   * and determine if they were built the same way.
   */
  public boolean eqv(TextBlock other) {
    boolean equality;

    if (other instanceof HorizontallyFlipped) { 
      // If other is also a HorizontallyFlipped TextBlock,
      //proceed to further comparison (comparing the contents of each)
      equality = this.textBlock.eqv(other.getContents()[0]);
    } else {
      // Otherwise, only this text block is a HorizontallyFlipped TextBlock, 
      // so they are not built the same way
      equality = false;
    }
    return equality;
  } // eqv(TextBlock)
} // class HorizontallyFlipped
