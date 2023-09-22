package proj;

import lab.polymorphism.TBUtils;
import lab.polymorphism.TextBlock;

/**
 * The truncation of a text block
 * 
 * @author Wenfei Lin
 */
public class Truncated implements TextBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The textblock to be truncated
   */
  TextBlock textBlock;

  /**
   * The new width, determining truncation behavior
   */
  int newWidth;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block by truncating the given textblock to the specified width
   */
  public Truncated(TextBlock _textBlock, int _newWidth) throws Exception {
    this.textBlock = _textBlock;
    if (_newWidth < 0) {
      throw new IllegalArgumentException("Negative width " + newWidth);
    } else { 
      // cannot have a negative width
      this.newWidth = _newWidth;
    }
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

  /**
   * Get the text block used to make the truncated block
   */
  public TextBlock[] getContents() {
    TextBlock[] contents = new TextBlock[] {textBlock};
    return contents;
  }
  
  /**
   * Compare this truncated block to another text block, other, 
   * and determine if they were built the same way
   */
  public boolean eqv(TextBlock other) {
    boolean equality;

    if (other instanceof Truncated) { // if both are Truncated TextBlocks,
      //proceed further comparison
      equality = this.textBlock.eqv(other.getContents()[0]);
    } else {
      equality = false;
    }

    return equality;
  } // eqv(TextBlock)
} // class Truncated
