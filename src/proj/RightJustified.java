package proj;

import lab.polymorphism.TBUtils;
import lab.polymorphism.TextBlock;

/**
 * A right-justified text block.
 * 
 * @author Wenfei Lin
 */
public class RightJustified implements TextBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The textblock to be right-justified in the composition.
   */
  TextBlock textBlock;

  /**
   * The width that the composition will be right-justified in.
   */
  int newWidth;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block by aligning the specified block to the right within 
   * the specified width.
   * 
   * @pre _newWidth >= 0
   * @exception Exception if the precondition is not met
   */
  public RightJustified(TextBlock _textBlock, int _newWidth) throws Exception {
    this.textBlock = _textBlock;
    if (_newWidth < 0) {
      throw new IllegalArgumentException("Negative width " + newWidth);
    } else { 
      // cannot have a negative width
      this.newWidth = _newWidth;
    }
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
      // Otherwise, add spaces to the left of original textBlock until newWidth
      int LeftPadding = newWidth - oldWidth;
      String padLeft = "";

      // Make the spaces for padding
      padLeft = TBUtils.spaces(LeftPadding);
      result = padLeft.concat(this.textBlock.row(i));
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
   * Get the text block used to make the right-justified block
   */
  public TextBlock[] getContents() {
    // Only input the textBlock used to make the right-justified block in the array
    TextBlock[] contents = new TextBlock[] {textBlock};
    return contents;
  } // getContents()
  
  /**
   * Compare this right-justified block to another text block, other, 
   * and determine if they were built the same way
   */
  public boolean eqv(TextBlock other) {
    boolean equality;
    
    if (other instanceof RightJustified) { // If other is also a RightJustified TextBlock,
      //proceed to further comparison (comparing the contents of each)
      equality = this.textBlock.eqv(other.getContents()[0]);
    } else {
      // Otherwise, only this text block is a RightJustified TextBlock, 
      // so they are not built the same way
      equality = false;
    }
    return equality;
  } // eqv(TextBlock)
} // class RightJustified
