package proj;

import lab.polymorphism.TextBlock;


public class VerticallyFlipped implements TextBlock{
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The textblock to be centered in the composition.
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

  public TextBlock[] getContents() {
    TextBlock[] contents = new TextBlock[] {textBlock};
    return contents;
  }

  public boolean eqv(TextBlock other) {
    boolean equality;

    if (other instanceof VerticallyFlipped) { // if both are Truncated TextBlocks,
      //proceed further comparison
      equality = this.textBlock.eqv(other.getContents()[0]);
    } else {
      equality = false;
    }
    return equality;
  } // eqv(TextBlock)
} // class VerticallyFlipped
