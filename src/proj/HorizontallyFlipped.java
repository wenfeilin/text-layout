package proj;
import lab.polymorphism.TextBlock;

public class HorizontallyFlipped implements TextBlock{
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
    //int rowWidth = this.textBlock.row(i).length();
    //int lastColumn = rowWidth - 1;

    // Sanity check
    if ((i < 0) || (i >= height)) {
      throw new Exception("Invalid row " + i);
    } // if the row is invalid

    // have to check that newWidth is not less than textblock's width
    // and if newWidth is negative
    // String result = "";

    // for (int columnIndex = 0; columnIndex < rowWidth; columnIndex++) {
    //   // can instead use a StringBuffer to then use its method, reverse() to reverse string instead
    //   result += this.textBlock.row(i).charAt(lastColumn - columnIndex);
    // }

    // this implementation might not be better (not sure)
    StringBuffer result = new StringBuffer (this.textBlock.row(i));
    return new String (result.reverse());
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

    if (other instanceof HorizontallyFlipped) { // if both are Truncated TextBlocks,
      //proceed further comparison
      equality = this.textBlock.eqv(other.getContents()[0]);
    } else {
      equality = false;
    }
    return equality;
  } // eqv(TextBlock)
} // class HorizontallyFlipped
