package proj;

import lab.polymorphism.TBUtils;
import lab.polymorphism.TextBlock;

public class Truncated implements TextBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The textblock to be truncated in the composition.
   */
  TextBlock textBlock;

  /**
   * The new width, determining truncation in the composition.
   */
  int newWidth;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block by truncating the given textblock to the specified width.
   */
  public Truncated(TextBlock _textBlock, int _newWidth) {
    this.textBlock = _textBlock;
    this.newWidth = _newWidth;
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
    int newWidth = this.newWidth;
    int oldWidth = this.textBlock.width();
    int height = this.textBlock.height();

    // Sanity check
    if ((i < 0) || (i >= height)) {
      // if the row is invalid
      throw new Exception("Invalid row " + i);
    } else if (newWidth < 0) { 
      // cannot have a negative width
      throw new Exception("Negative width" + newWidth);
    }

    String result;
    
    if (newWidth > oldWidth) { 
      // if the width of the new textBlock is greater than the original width, 
      // then add spaces to the right of the original textBlock
      int numofSpaces = newWidth - oldWidth;
      String padRight = TBUtils.spaces(numofSpaces);

      result = this.textBlock.row(i).concat(padRight);
    } else {
      // truncate contents of textBlock to only include contents up to the new width 
      // of the textBlock
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
    return this.newWidth;
  } // width()

  public TextBlock getContents() {
    return textBlock;
  }
  
  public boolean eqv(TextBlock other) {
    boolean equality;

    if (other instanceof Truncated) { // if both are Truncated TextBlocks,
      //proceed further comparison
      equality = this.textBlock.eqv(other.getContents());
    } else {
      equality = false;
    }
    return equality;
  } // eqv(TextBlock)
} // class Truncated
