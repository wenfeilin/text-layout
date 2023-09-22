package proj;

import lab.polymorphism.TBUtils;
import lab.polymorphism.TextBlock;

/**
 * A text block with a smiley face on it 
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
  } // SmilelyBlock(TextBlock, int)

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

    if (contentsWidth == 0) {
      leftContentsPadding = "";
      rightContentsPadding = "";
      eyePadding = "";
    } else if (!(contentsWidth % 2 == 0)) { // if the contents of textBlock has odd width (preferable for centering the smile)
      leftContentsPadding = TBUtils.spaces((contentsWidth - 1) / 2);
      rightContentsPadding = leftContentsPadding;
      eyePadding = TBUtils.spaces((contentsWidth + 1) / 2);
    } else { // if the contents of textBlock has even width
      leftContentsPadding = TBUtils.spaces(contentsWidth / 2);
      rightContentsPadding = TBUtils.spaces((contentsWidth / 2) - 1);
      eyePadding = TBUtils.spaces(contentsWidth / 2);
    }

    if (newHeight % 2 == 0) {
      middleRowIndex = (newHeight / 2) - 1;
    } else {
      middleRowIndex = ((newHeight + 1) / 2) - 1;
    }

    // Sanity check
    if ((i < 0) || (i >= newHeight)) {
      // if the row is invalid
      throw new Exception("Invalid row " + i);
    } 

    String result;
    
    if (contentsWidth == 0 && i == middleRowIndex) {
      result = blushPadding + "O" + eyePadding + " " + eyePadding + "O" + blushPadding;
    } else if (i == middleRowIndex) {
      result = blushPadding + "O" + eyePadding + this.textBlock.row(i) + eyePadding + "O" + blushPadding;
    } else if (i == newHeight - 1) {
      result = "~" + blushPadding + eyePadding + leftContentsPadding + "U" + rightContentsPadding + eyePadding + blushPadding + "~";
    } else {
      result = blushPadding + " " + eyePadding + this.textBlock.row(i) + eyePadding + " " + blushPadding;
    }

    return result;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   */
  public int height() {
    return this.textBlock.height() + 1;
  } // height()

  /**
   * Determine how many columns are in the block.
   */
  public int width() {

    // check to make sure this is right

    int eyePadding;
    int contentsPadding;
    int blushPadding = 3 * 2;
    int blushes = 2;
    int mouth = 1;
    int contentsWidth = this.textBlock.width();

    if (!(contentsWidth % 2 == 0)) { // if content's width is odd
      // determine total eyePadding and contentsPadding
      eyePadding = contentsWidth + 1;
      contentsPadding = contentsWidth - 1;
    } else { // if content's width is even
      // determine total eyePadding and contentsPadding
      eyePadding = contentsWidth;
      contentsPadding = contentsWidth - 1;
    }

    return contentsWidth + contentsPadding + eyePadding + blushPadding + blushes + mouth;
  } // width()

  /**
   * Get the text block used to make the smiley block
   */
  public TextBlock[] getContents() {
    TextBlock[] contents = new TextBlock[] {textBlock};
    return contents;
  }
  
  /**
   * Compare this smiley block to another text block, other, 
   * and determine if they were built the same way
   */
  public boolean eqv(TextBlock other) {
    boolean equality;

    if (other instanceof SmileyBlock) { // if both are Truncated TextBlocks,
      //proceed further comparison
      equality = this.textBlock.eqv(other.getContents()[0]);
    } else {
      equality = false;
    }
    return equality;
  } // eqv(TextBlock)
} // class SmileyBlock
