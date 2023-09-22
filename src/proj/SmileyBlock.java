package proj;

import lab.polymorphism.TBUtils;
import lab.polymorphism.TextBlock;

/**
 * A text block with a smiley face on it. 
 * 
 * @author Wenfei Lin
 */
public class SmileyBlock implements TextBlock{
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The textblock to be modified to have a smiley face on it.
   */
  TextBlock textBlock;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block by adding a smiley face on the textBlock.
   */
  public SmileyBlock(TextBlock _textBlock) {
    this.textBlock = _textBlock;
  } // SmileyBlock(TextBlock, int)

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
    int newHeight = this.height();
    int middleRowIndex;
    int contentsWidth = this.textBlock.width();
    String blushPadding = TBUtils.spaces(3);
    String leftContentsPadding;
    String rightContentsPadding;
    String eyePadding;

    if (contentsWidth == 0) { // If the an empty TextLine is given, 
      // there is no padding
      leftContentsPadding = ""; 
      rightContentsPadding = "";
      eyePadding = "";
    } else if (!(contentsWidth % 2 == 0)) { 
      // If the contents of textBlock has an odd width (preferable for centering the smile),
      // determine the padding 

      // Contents padding is the padding from the eye padding to the place where the mouth is
      // (left) and the padding from the mouth to the other eye's position (right)
      leftContentsPadding = TBUtils.spaces((contentsWidth - 1) / 2);
      rightContentsPadding = leftContentsPadding;
      // Eye padding is the padding from the eye, "O", to the contents of the block
      eyePadding = TBUtils.spaces((contentsWidth + 1) / 2);
    } else { 
      // If the contents of textBlock has an even width,
      // determine the padding
      leftContentsPadding = TBUtils.spaces(contentsWidth / 2);
      rightContentsPadding = TBUtils.spaces((contentsWidth / 2) - 1);
      eyePadding = TBUtils.spaces(contentsWidth / 2);
    }

    // The middle row is where the eyes for the smiley face will be placed
    if (newHeight % 2 == 0) { 
      // If the new height of this block is even, 
      // choose the row above the halfway point of the block to be 
      // the middle row
      middleRowIndex = (newHeight / 2) - 1;
    } else {
      // If the new height of this block is odd, 
      // choose the row right on the middle as the middle row
      middleRowIndex = ((newHeight + 1) / 2) - 1;
    }

    // Sanity check
    if ((i < 0) || (i >= newHeight)) {
      // if the row is invalid
      throw new Exception("Invalid row " + i);
    } 

    String result;
    
    if (contentsWidth == 0 && i == middleRowIndex) {
      // If an empty TextLine is given, everything is the same when building the row with the eyes,
      // except add a space between the eyes
      result = blushPadding + "O" + eyePadding + " " + eyePadding + "O" + blushPadding;
    } else if (i == middleRowIndex) {
      // If i is the middle row, build the row with eyes
      result = blushPadding + "O" + eyePadding 
              + this.textBlock.row(i) + eyePadding + "O" + blushPadding;
    } else if (i == newHeight - 1) {
      // If i is the last row, build the row with the blush and mouth
      result = "~" + blushPadding + eyePadding + leftContentsPadding + "U" 
              + rightContentsPadding + eyePadding + blushPadding + "~";
    } else {
      // Otherwise, this is a row without any smiley face features, so 
      // print the content of the block at that row but with padding
      result = blushPadding + " " + eyePadding + this.textBlock.row(i) 
              + eyePadding + " " + blushPadding;
    }

    return result;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   */
  public int height() {
    // accounts for the extra row for the mouth and blush
    return this.textBlock.height() + 1;
  } // height()

  /**
   * Determine how many columns are in the block.
   */
  public int width() {
    int eyePadding;
    int blushPadding = 3 * 2; // blush padding * num of blushes
    int blushes = 2;
    int extraSpace;
    int contentsWidth = this.textBlock.width();

    if (contentsWidth == 0) {
      // If given an empty TextLine, don't include eye padding and put an extra space 
      // between the eyes
      eyePadding = 0;
      extraSpace = 1;
    } else if (!(contentsWidth % 2 == 0)) { 
      // If content's width is odd, determine total eyePadding
      eyePadding = contentsWidth + 1;
      extraSpace = 0;
    } else { 
      // If content's width is even, determine total eyePadding
      eyePadding = contentsWidth;
      extraSpace = 0;
    }

    // returning the width calculated based on the last row of a SmileyBlock
    return contentsWidth + eyePadding + blushPadding + blushes + extraSpace;
  } // width()

  /**
   * Get the text block used to make the smiley block.
   */
  public TextBlock[] getContents() {
    // Only input the textBlock used to make the smiley block in the array
    TextBlock[] contents = new TextBlock[] {textBlock};
    return contents;
  } // getContents()
  
  /**
   * Compare this smiley block to another text block, other, 
   * and determine if they were built the same way.
   */
  public boolean eqv(TextBlock other) {
    boolean equality;

    if (other instanceof SmileyBlock) { // If other is also a SmileyBlock,
      // proceed to further comparison (comparing the contents of each)
      equality = this.textBlock.eqv(other.getContents()[0]);
    } else {
      // Otherwise, only this text block is a SmileyBlock, 
      // so they are not built the same way
      equality = false;
    }
    return equality;
  } // eqv(TextBlock)
} // class SmileyBlock
