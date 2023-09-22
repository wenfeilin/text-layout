package proj;

import lab.polymorphism.TBUtils;
import lab.polymorphism.TextBlock;

/**
 * A text block that is centered
 * 
 * @author Wenfei Lin
 */
public class Centered implements TextBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The textblock to be centered in the composition.
   */
  TextBlock textBlock;

  /**
   * The new width that the composition will be centered in.
   */
  int newWidth;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block by centering the specified textblock in the specified width.
   */
  public Centered(TextBlock _textBlock, int _newWidth) {
    this.textBlock = _textBlock;
    this.newWidth = _newWidth;
  } // Centered(TextBlock, int)

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
  
    // Sanity check
    if ((i < 0) || (i >= height)) {
      // if the row is invalid
      throw new Exception("Invalid row " + i);
    } else if (newWidth < 0) { 
      // cannot have a negative width
      throw new Exception("Negative width " + newWidth);
    }

    // have to check that newWidth is not less than textblock's width
    // and if newWidth is negative
    String result;
    if (newWidth < oldWidth) { 
      // having a new width less than the textBlock's width will truncate the textBlock
      result = this.textBlock.row(i).substring(0, newWidth);
    } else { // add equal spaces on left and right side of original textBlock
      int totalPadding = newWidth - oldWidth;

      String padLeft = "";
      String padRight = "";
      if (totalPadding % 2 == 0) {
        padLeft = TBUtils.spaces(totalPadding / 2);
        padRight = TBUtils.spaces(totalPadding / 2);
      } else {
        padLeft = TBUtils.spaces(totalPadding / 2 + 1);
        padRight = TBUtils.spaces(totalPadding / 2);
      }

      result = padLeft.concat(this.textBlock.row(i)).concat(padRight);
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
   * Get the text block used to make the centered block
   */
  public TextBlock[] getContents() {
    TextBlock[] contents = new TextBlock[] {textBlock};
    return contents;
  }

  /**
   * Compare this centered block to another text block, other, 
   * and determine if they were built the same way
   */
  public boolean eqv(TextBlock other) {
    boolean equality;

    if (other instanceof Centered) { // if both are Truncated TextBlocks,
      //proceed further comparison
      equality = this.textBlock.eqv(other.getContents()[0]);
    } else {
      equality = false;
    }
    return equality;
  } // eqv(TextBlock)
  
} // class Centered
