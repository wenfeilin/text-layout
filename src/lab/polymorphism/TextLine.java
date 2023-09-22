package lab.polymorphism;

/**
 * One line of text.
 * 
 * @author Samuel A. Rebelsky
 * @author Wenfei Lin
 */
public class TextLine implements TextBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The contents of the line.
   */
  String line;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new line with the specified contents.
   */
  public TextLine(String line) {
    this.line = line;
  } // TextLine(String)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   * 
   * @pre i == 0
   * @exception Exception if i != 0
   */
  public String row(int i) throws Exception {
    if (i != 0) {
      throw new Exception("Invalid row " + i);
    } // if the row is invalid
    return this.line;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   */
  public int height() {
    return 1;
  } // height()

  /**
   * Determine how many columns are in the block.
   */
  public int width() {
    return this.line.length();
  } // width()

  /**
   * Get the text block used to make the text line
   */
  public TextBlock[] getContents() { 
    // There is no text block used to make a TextLine, only a String
    // This is just a requirement from the interface, but nothing is put into the array
    TextBlock[] contents = new TextBlock[] {};
    return contents;
  } // getContents()
  
  /**
   * Compare this text line to another text block, other, and determine if they are the same type
   */ 
  public boolean eqv(TextBlock other) { 
    // If other is also a TextLine, confirm the two are built the same way 
    // TextLines are the lowest level of TextBlocks and are used to compose
    // other text blocks so the lowest level in any text block is a TextLine
    return other instanceof TextLine;
  } // eqv(TextBlock)
} // class TextLine
