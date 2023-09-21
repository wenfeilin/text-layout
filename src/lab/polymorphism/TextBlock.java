package lab.polymorphism;
/**
 * Simple blocks of text for a lab on polymorphism
 * 
 * @author Samuel A. Rebelsky
 * @version 1.2 of February 2019
 */
public interface TextBlock {
  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   * 
   * @pre i < this.height()
   * @exception Exception if the row number is invalid.
   */
  public String row(int i) throws Exception;

  /**
   * Determine how many rows are in the block.
   */
  public int height();

  /**
   * Determine how many columns are in the block.
   */
  public int width();
  

  /** 
   * Retrieves only the textBlock type contents of a textBlock
   */
  public TextBlock[] getContents(); 

  /**
   * Compare if this TextBlock and other are equal based on how they were created.
   */
  public boolean eqv(TextBlock other);
} // interface TextBlock
