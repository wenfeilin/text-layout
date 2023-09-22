package proj;

import lab.polymorphism.TBUtils;
import lab.polymorphism.TextBlock;

/**
 * A text block that is centered.
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
   * Build a new block by centering the specified textblock in the specified 
   * width.
   * 
   * @pre _newWidth >= 0
   * @exception Exception if the precondition is not met
   */
  public Centered(TextBlock _textBlock, int _newWidth) throws Exception{
    this.textBlock = _textBlock;
    if (_newWidth < 0) {
      throw new IllegalArgumentException("Negative width " + newWidth);
    } else { 
      // cannot have a negative width
      this.newWidth = _newWidth;
    }    
  } // Centered(TextBlock, int)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   * 
   * @pre 0 <= i < this.height()
   * @pre this.newWidth >= 0
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
    } 

    String result;
    if (newWidth < oldWidth) { 
      // Having a new width less than the textBlock's width will truncate the textBlock
      result = this.textBlock.row(i).substring(0, newWidth);
    } else { 
      // Otherwise, add equal spaces on left and right side of original textBlock until newWidth
      int totalPadding = newWidth - oldWidth;

      String padLeft = "";
      String padRight = "";
      if (totalPadding % 2 == 0) {
        // Equal left and right padding if the total padding was even
        padLeft = TBUtils.spaces(totalPadding / 2);
        padRight = TBUtils.spaces(totalPadding / 2);
      } else {
        // Unequal left and right padding if the total padding was even
        // The new text block will be slightly to the right of the center
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
    return this.textBlock.height(); // same
  } // height()

  /**
   * Determine how many columns are in the block.
   */
  public int width() {
    return this.newWidth; // same
  } // width()

  /**
   * Get the text block used to make the centered block.
   */
  public TextBlock[] getContents() {
    // Only input the textBlock used to make the centered block in the array
    TextBlock[] contents = new TextBlock[] {textBlock};
    return contents;
  } // getContents()

  /**
   * Compare this centered block to another text block, other, 
   * and determine if they were built the same way.
   */
  public boolean eqv(TextBlock other) {
    boolean equality;

    if (other instanceof Centered) { // If other is also a Centered TextBlock,
      // proceed to further comparison (comparing the contents of each)
      equality = this.textBlock.eqv(other.getContents()[0]);
    } else {
      // Otherwise, only this text block is a Centered TextBlock, 
      // so they are not built the same way
      equality = false;
    }
    return equality;
  } // eqv(TextBlock)
} // class Centered
