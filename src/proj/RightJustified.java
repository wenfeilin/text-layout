package proj;
import lab.polymorphism.TBUtils;
import lab.polymorphism.TextBlock;

public class RightJustified implements TextBlock {
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
  int newWidth;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block by aligning the specified block to the right within the specified width.
   */
  public RightJustified(TextBlock _textBlock, int _newWidth) {
    this.textBlock = _textBlock;
    this.newWidth = _newWidth;
  } // RightJustified(TextBlock, int)

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
    int newWidth = this.newWidth;
    int oldWidth = this.textBlock.width();
    int LeftPadding = newWidth - oldWidth;

    String padLeft = "";
    padLeft = TBUtils.spaces(LeftPadding);

    // Sanity check
    if ((i < 0) || (i >= height)) {
      // if the row is invalid
      throw new Exception("Invalid row " + i);
    } else if (newWidth < 0) { 
      // cannot have a negative width
      throw new Exception("Negative width" + newWidth);
    }// if the row is invalid

    String result;
    if (newWidth < oldWidth) {
      // having a new width less than the textBlock's width will truncate the textBlock
      result = this.textBlock.row(i).substring(0, newWidth);
    } else {
      result = padLeft.concat(this.textBlock.row(i));
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
    return this.newWidth;
  } // width()

  public TextBlock[] getContents() {
    TextBlock[] contents = new TextBlock[] {textBlock};
    return contents;
  }

  public boolean eqv(TextBlock other) {
    boolean equality;

    if (other instanceof RightJustified) { // if both are Truncated TextBlocks,
      //proceed further comparison
      equality = this.textBlock.eqv(other.getContents()[0]);
    } else {
      equality = false;
    }
    return equality;
  } // eqv(TextBlock)
} // class RightJustified
