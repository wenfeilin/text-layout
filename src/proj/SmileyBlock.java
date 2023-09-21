package proj;
import lab.polymorphism.TBUtils;
import lab.polymorphism.TextBlock;

public class SmileyBlock implements TextBlock{
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The textblock to be truncated in the composition.
   */
  TextBlock textBlock;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block by underlining the the textBlock.
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
    String blushPadding = TBUtils.spaces(3);
    String leftContentsPadding;
    String rightContentsPadding;
    String eyePadding;

    if (!(this.textBlock.width() % 2 == 0)) { // if the contents of textBlock is odd length (preferable for centering the smile)
      leftContentsPadding = TBUtils.spaces(this.textBlock.width() - 1 / 2);
      rightContentsPadding = leftContentsPadding;
      eyePadding = TBUtils.spaces(this.textBlock.width() + 1 / 2);
    } else { // if the contents of textBlock is odd length (preferable for centering the eyes)
      leftContentsPadding = TBUtils.spaces(this.textBlock.width() / 2);
      rightContentsPadding = TBUtils.spaces(this.textBlock.width() / 2 - 1);
      eyePadding = TBUtils.spaces(this.textBlock.width() / 2);
    }

    if (newHeight % 2 == 0) {
      middleRowIndex = newHeight / 2 - 1;
    } else {
      middleRowIndex = newHeight + 1 / 2 - 1;
    }

    // Sanity check
    if ((i < 0) || (i >= newHeight)) {
      // if the row is invalid
      throw new Exception("Invalid row " + i);
    } 

    String result;
    
    if (i == middleRowIndex) {
      result = blushPadding + "O" + eyePadding + this.textBlock.row(i) + eyePadding + "O" + blushPadding;
    } else if (i == newHeight - 1) {
      result ="~" + blushPadding + eyePadding + leftContentsPadding + "U" + rightContentsPadding + eyePadding + blushPadding + "~";
    } else {
      result = this.textBlock.row(i);
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
    int eyePadding;
    int blushPadding = 3;
    int blushes = 2;

    if (!(this.textBlock.width() % 2 == 0)) {
      eyePadding = this.textBlock.width() + 1;
    } else {
      eyePadding = this.textBlock.width();
    }

    return this.textBlock.width() + eyePadding + (blushPadding * 2) + blushes;
  } // width()

  public TextBlock[] getContents() {
    TextBlock[] contents = new TextBlock[] {textBlock};
    return contents;
  }
  
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
