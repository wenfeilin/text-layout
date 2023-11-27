package proj;

import lab.polymorphism.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for TextBlock methods.
 * 
 * @author Wenfei Lin
 */
public class TextBlockTests {
  // Basic Text Blocks to build more complex text blocks or for edge case testing
  TextLine line, emptyLine;
  BoxedBlock box, boxInABox, emptyBox;

  // Text Blocks for eqv, equal, and eq tests
  VComposition sameBuildDiffLooksObj1, sameBuildDiffLooksObj2, diffBuildSameLooksObj1;
  HorizontallyFlipped sameBuildSameLooksObj1, sameBuildSameLooksObj2, hFlippedPalindrome, 
      copyOfhFlippedPalindrome;
  VerticallyFlipped diffBuildSameLooksObj2;
  SmileyBlock diffBuildDiffLooksObj1, diffBuildDiffLooksObj2;

  // Some standard blocks of each type for block combination tests:
  Centered aCenteredBlock;
  RightJustified aRightJustifiedBlock;
  SmileyBlock aSmileyBlock;
  Truncated aTruncatedBlock;
  HorizontallyFlipped aHorizontallyFlippedBlock;
  VerticallyFlipped aVerticallyFlippedBlock;

  /**
   * The setup of some TextBlock objects to be used in the tests.
   */
  @Before
  public void setUp() throws Exception {
    int rightJustifiedLineWidth = 17;

    // Basic Text Boxes to build with in following tests:
    line = new TextLine("Stars");
    box = new BoxedBlock(line);
    emptyLine = new TextLine("");
    emptyBox = new BoxedBlock(emptyLine);
    boxInABox = new BoxedBlock(new BoxedBlock(line));

    // Standard text blocks for block combination tests:
    aCenteredBlock = new Centered(line, 13);
    aRightJustifiedBlock = new RightJustified(line, 12);
    aSmileyBlock = new SmileyBlock(line);
    aTruncatedBlock = new Truncated(line, 3);
    aHorizontallyFlippedBlock = new HorizontallyFlipped(line);
    aVerticallyFlippedBlock = new VerticallyFlipped(line);

    // Text Blocks for testing eqv, equal, eq methods:

    // Text Blocks that are built the same but look different
    sameBuildDiffLooksObj1 = new VComposition(new RightJustified(
        new BoxedBlock(new TextLine("potatoes")), rightJustifiedLineWidth), emptyBox);
    sameBuildDiffLooksObj2 = new VComposition(new RightJustified(
        new BoxedBlock(new TextLine("fries")), rightJustifiedLineWidth), emptyBox);

    // Text Blocks that are built the same and look the same
    sameBuildSameLooksObj1 = new HorizontallyFlipped(new HComposition(new SmileyBlock(
        new BoxedBlock(new TextLine("Reading backwards"))), new TextLine(":D")));
    sameBuildSameLooksObj2 = new HorizontallyFlipped(new HComposition(new SmileyBlock(
        new BoxedBlock(new TextLine("Reading backwards"))), new TextLine(":D")));

    // Text Blocks that are built different but look the same
    diffBuildSameLooksObj1 = new VComposition(new BoxedBlock(line),
        new TextLine("in the sky"));
    diffBuildSameLooksObj2 = new VerticallyFlipped(new VerticallyFlipped(new VComposition(
        new BoxedBlock(line), new TextLine("in the sky"))));

    // Text Blocks that are built different and look different
    diffBuildDiffLooksObj1 = new SmileyBlock(new BoxedBlock(new SmileyBlock(
        new TextLine(" ~   O U O   ~"))));
    diffBuildDiffLooksObj2 = new SmileyBlock(new SmileyBlock(new TextLine("T n T")));

    // Visually identical text blocks but two different objects (occupy different
    // spaces in memory):
    hFlippedPalindrome = new HorizontallyFlipped(new TextLine("LeveL"));
    copyOfhFlippedPalindrome = new HorizontallyFlipped(new TextLine("LeveL"));
  } // setUp()

  // Centered Blocks

  /**
   * Tests to check that what is printed by the centered TextBlock is the same as
   * what is expected
   * to be printed.
   * TBUtils.toString(TextBlock) uses the row(int) method from TextBlocks,
   * so if the output from the former method is equal to the expected String,
   * then row(int) shows expected behavior (`row` works as a method)
   */
  @Test
  public void centeredBlockRowTests() throws Exception {
    Centered sameWidthCentered = new Centered(line, 5);
    Centered centeredNormally = new Centered(line, 17);
    Centered centeredUnevenly = new Centered(line, 6);
    Centered smallWidthCentered = new Centered(line, 3);
    Centered width0Centered = new Centered(line, 0);

    assertEquals("centering with the same width does nothing", "Stars",
        TBUtils.toString(sameWidthCentered));
    assertEquals("even centering", "      Stars      ",
        TBUtils.toString(centeredNormally));
    assertEquals("uneven centering", " Stars",
        TBUtils.toString(centeredUnevenly));
    assertEquals("centering with width less than TextBlock's width truncates block", "Sta",
        TBUtils.toString(smallWidthCentered));
    assertEquals("centering with new width of 0", "",
        TBUtils.toString(width0Centered));
  } // centeredBlockRowTests()

  /**
   * Tests for confirming the width and height of centered TextBlocks.
   */
  @Test
  public void centeredBlockWidthHeightTests() throws Exception {
    Centered centeredBlock = new Centered(line, 20);

    assertEquals("width of a centered block is the new width", 20, centeredBlock.width());
    assertEquals("height of a centered block stays the same", 1,
        centeredBlock.height());
  } // centeredBlockWidthHeightTests()

  /**
   * Tests for proper centering of other Text Block types and for Centered block
   * types.
   */
  @Test
  public void centeredBlockCombinationTests() throws Exception {
    // centering a centered block:
    assertTrue(TBUtils.equal(new TextLine("        Stars        "), 
        new Centered(aCenteredBlock, 21)));

    // centering a horizontally-flipped block:
    assertTrue(TBUtils.equal(new TextLine("        sratS        "), 
        new Centered(aHorizontallyFlippedBlock, 21)));

    // centering a right-justified block:
    assertTrue(TBUtils.equal(new TextLine("            Stars     "), 
        new Centered(aRightJustifiedBlock, 22)));

    // centering a smiley block:
    assertTrue(TBUtils.equal(new VComposition(new TextLine("    O   Stars   O    "), 
        new TextLine(" ~        U        ~ ")),
        new Centered(aSmileyBlock, 21)));

    // centering a truncated block:
    assertTrue(TBUtils.equal(new TextLine("         Sta         "), 
        new Centered(aTruncatedBlock, 21)));

    // centering a vertically-flipped block:
    assertTrue(TBUtils.equal(new TextLine("        Stars        "), 
        new Centered(aVerticallyFlippedBlock, 21)));
  } // centeredBlockCombinationTests()

  /**
   * Tests for empty centered blocks (edge case)
   */
  @Test
  public void emptyCenteredBlockTest() throws Exception {
    Centered emptyCenteredBlock = new Centered(emptyLine, 3);
    assertEquals("   ", TBUtils.toString(emptyCenteredBlock));
  } // emptyCenteredBlockTest()

  // Right Justified Blocks

  /**
   * Tests to check that what is printed by the right-justified TextBlock is the
   * same as what is expected
   * to be printed.
   * TBUtils.toString(TextBlock) uses the row(int) method from TextBlocks,
   * so if the output from the former method is equal to the expected String,
   * then row(int) shows expected behavior (`row` works as a method)
   */
  @Test
  public void rightJustifiedBlockRowTests() throws Exception {
    RightJustified rightJustifiedNormally = new RightJustified(line, 17);
    RightJustified noRightJustification = new RightJustified(line, 5);
    RightJustified smallWidthRightJustified = new RightJustified(line, 3);
    RightJustified width0RightJustified = new RightJustified(line, 0);

    assertEquals("a line right-justified within a width of 17", "            Stars",
        TBUtils.toString(rightJustifiedNormally));
    assertEquals("right-justifying with the same width does nothing", "Stars",
        TBUtils.toString(noRightJustification));
    assertEquals("", "Sta", TBUtils.toString(smallWidthRightJustified));
    assertEquals("right-justifying with new width of 0", "",
        TBUtils.toString(width0RightJustified));
  } // rightJustifiedBlockRowTests()

  /**
   * Tests for confirming the width and height of right-justified TextBlocks.
   */
  @Test
  public void rightJustifiedBlockWidthHeightTests() throws Exception {
    RightJustified rightJustifiedBlock = new RightJustified(line, 20);

    assertEquals("width of a right-justified block is the new width", 20,
        rightJustifiedBlock.width());
    assertEquals("height of a right-justified block stays the same", line.height(),
        rightJustifiedBlock.height());
  } // rightJustifiedBlockWidthHeightTests()

  /**
   * Tests for proper right justification of other Text Block types and for
   * RightJustified block types.
   */
  @Test
  public void rightJustifiedBlockCombinationTests() throws Exception {
    // right-justifying a right-justified block:
    assertTrue(TBUtils.equal(new TextLine("                Stars"), 
        new RightJustified(aRightJustifiedBlock, 21)));

    // right-justifying a centered block:
    assertTrue(TBUtils.equal(new TextLine("            Stars    "), 
        new RightJustified(aCenteredBlock, 21)));

    // right-justifying a horizontally-flipped block:
    assertTrue(TBUtils.equal(new TextLine("                sratS"), 
        new RightJustified(aHorizontallyFlippedBlock, 21)));

    // right-justifying a smiley block:
    assertTrue(TBUtils.equal(new VComposition(new TextLine("     O   Stars   O   "), 
        new TextLine("  ~        U        ~")),
        new RightJustified(aSmileyBlock, 21)));

    // right-justifying a truncated block:
    assertTrue(TBUtils.equal(new TextLine("                  Sta"), 
        new RightJustified(aTruncatedBlock, 21)));

    // right-justifying a vertically-flipped block:
    assertTrue(TBUtils.equal(new TextLine("                Stars"), 
        new RightJustified(aVerticallyFlippedBlock, 21)));
  } // rightJustifiedBlockCombinationTests()

  /**
   * Tests for empty right-justified blocks (edge case)
   */
  @Test
  public void emptyRightJustifiedBlockTests() throws Exception {
    RightJustified emptyRightJustifiedBlock = new RightJustified(emptyLine, 3);
    assertEquals("   ", TBUtils.toString(emptyRightJustifiedBlock));
  } // emptyRightJustifiedBlockTests()

  // Smiley Blocks

  /**
   * Tests to check that what is printed by the smiley TextBlock is the same as
   * what is expected
   * to be printed.
   * TBUtils.toString(TextBlock) uses the row(int) method from TextBlocks,
   * so if the output from the former method is equal to the expected String,
   * then row(int) shows expected behavior (`row` works as a method)
   */
  @Test
  public void smileyBlockRowTests() throws Exception {
    SmileyBlock smileyTextLine = new SmileyBlock(line);
    SmileyBlock smileyMouthInMiddle = new SmileyBlock(line);
    SmileyBlock smileyMouthNotInMiddle = new SmileyBlock(new TextLine("Uneven"));
    SmileyBlock longSmileyBlock = new SmileyBlock(new TextLine("Loooooooooooong text"));
    SmileyBlock shortSmileyBlock = new SmileyBlock(new TextLine("short"));
    SmileyBlock tallSmileyBlock = new SmileyBlock(new VComposition(new BoxedBlock(
        new TextLine("ABOVE THE")), line));

    assertEquals("a line with a smiley face on it",
        "   O   Stars   O   \n" +
            "~        U        ~",
        TBUtils.toString(smileyTextLine));
    assertEquals("mouth is centered under the text block",
        "   O   Stars   O   \n" +
            "~        U        ~",
        TBUtils.toString(smileyMouthInMiddle));
    assertEquals("mouth is not centered under the text block",
        "   O   Uneven   O   \n" +
            "~         U        ~",
        TBUtils.toString(smileyMouthNotInMiddle));
    assertEquals("example of spacing increasing in creation of smileyBlock" +
        "when given a longer block",
        "   O          Loooooooooooong text          O   \n" +
            "~                       U                      ~",
        TBUtils.toString(longSmileyBlock));
    assertEquals("example of spacing decreasing in creation of smileyBlock" +
        "when given a shorter block",
        "   O   short   O   \n" +
            "~        U        ~",
        TBUtils.toString(shortSmileyBlock));
    assertEquals("smiley face on a tall vertical composition",
        "          +---------+          \n" +
            "          |ABOVE THE|          \n" +
            "   O      +---------+      O   \n" +
            "          Stars                \n" +
            "~              U              ~",
        TBUtils.toString(tallSmileyBlock));
  } // smileyBlockRowTests()

  /**
   * Tests for confirming the width and height of smiley TextBlocks.
   */
  @Test
  public void smileyBlockWidthHeightTests() throws Exception {
    SmileyBlock emptySmileyBlock = new SmileyBlock(emptyLine);
    SmileyBlock smileyTextLine = new SmileyBlock(line);
    SmileyBlock tallSmileyBlock = new SmileyBlock(new VComposition(new BoxedBlock(
        new TextLine("ABOVE THE")), line));

    assertEquals("width of just a smiley face", 9, emptySmileyBlock.width());
    assertEquals("width of line with a smiley face", 19, smileyTextLine.width());

    assertEquals("height of line with a smiley face", 2, smileyTextLine.height());
    assertEquals("height of tall block with a smiley face", 5, tallSmileyBlock.height());
  } // smileyBlockWidthHeightTests()

  /**
   * Tests for proper transformation into a smiley block of other Text Block types
   * and for SmileyBlock block types.
   */
  @Test
  public void smileyBlockCombinationTests() throws Exception {
    // turning a smiley block into a smiley block:
    assertTrue(TBUtils.equal(
        new VComposition(new VComposition(
          new TextLine("                 O   Stars   O                 "),
          new TextLine("   O          ~        U        ~          O   ")),
          new TextLine("~                      U                      ~")),
        new SmileyBlock(aSmileyBlock)));
    // turning a right-justified block into a smiley block:
    assertTrue(TBUtils.equal(new VComposition(new TextLine("   O             Stars      O"),
        new TextLine("~               U              ~")), new SmileyBlock(aRightJustifiedBlock)));
    // turning a centered block into a smiley block:
    assertTrue(TBUtils.equal(new VComposition(new TextLine("   O           Stars           O   "),
        new TextLine("~                U                ~")), new SmileyBlock(aCenteredBlock)));
    // turning a horizontally-flipped block into a smiley block:
    assertTrue(TBUtils.equal(new VComposition(new TextLine("   O   sratS   O   "), 
        new TextLine("~        U        ~")),
        new SmileyBlock(aHorizontallyFlippedBlock)));
    // turning a truncated block into a smiley block:
    assertTrue(TBUtils.equal(new VComposition(new TextLine("   O  Sta  O   "), 
        new TextLine("~      U      ~")),
        new SmileyBlock(aTruncatedBlock)));
    // turning a vertically-flipped block into a smiley block:
    assertTrue(TBUtils.equal(new VComposition(new TextLine("   O   Stars   O   "), 
        new TextLine("~        U        ~")),
        new SmileyBlock(aVerticallyFlippedBlock)));
  } // smileyBlockCombinationTests()

  /**
   * Tests for empty smiley blocks (edge case)
   */
  @Test
  public void emptySmileyBlockTests() throws Exception {
    SmileyBlock emptySmileyBlock = new SmileyBlock(emptyLine);
    assertEquals("   O O   \n" +
        "~   U   ~", TBUtils.toString(emptySmileyBlock));
  } // emptySmileyBlockTests()

  // Truncated Blocks

  /**
   * Tests to check that what is printed by the truncated TextBlock is the same as
   * what is expected
   * to be printed.
   * TBUtils.toString(TextBlock) uses the row(int) method from TextBlocks,
   * so if the output from the former method is equal to the expected String,
   * then row(int) shows expected behavior (`row` works as a method)
   */
  @Test
  public void truncatedBlockRowTests() throws Exception {
    Truncated truncatedNormally = new Truncated(line, 3);
    Truncated truncatedBox = new Truncated(box, 3);
    Truncated width0TruncatedBlock = new Truncated(line, 0);
    Truncated sameWidthTruncatedBlock = new Truncated(line, 5);
    Truncated largeWidthTruncatedBlock = new Truncated(line, 6);

    assertEquals("a line truncated to width of three", "Sta", TBUtils.toString(truncatedNormally));
    assertEquals("truncating with the same width does nothing", "Stars",
        TBUtils.toString(sameWidthTruncatedBlock));
    assertEquals("truncating with a larger width expands the text block", "Stars ",
        TBUtils.toString(largeWidthTruncatedBlock));
    assertEquals("truncating a box",
        "+--\n" +
            "|St\n" +
            "+--",
        TBUtils.toString(truncatedBox));
    assertEquals("truncating with new width of 0", "", TBUtils.toString(width0TruncatedBlock));
  } // truncatedBlockRowTests()

  /**
   * Tests for confirming the width and height of truncated TextBlocks.
   */
  @Test
  public void truncatedBlockWidthHeightTests() throws Exception {
    Truncated truncatedBlock = new Truncated(line, 2);

    assertEquals("width of a truncated block is the new width", 2, truncatedBlock.width());
    assertEquals("height of a truncated block stays the same", line.height(),
        truncatedBlock.height());
  } // truncatedBlockWidthHeightTests()

  /**
   * Tests for proper truncation of other Text Block types and for Truncated block
   * types.
   */
  @Test
  public void truncatedBlockCombinationTests() throws Exception {
    // truncating a truncated block:
    assertTrue(TBUtils.equal(new TextLine("S"), new Truncated(aTruncatedBlock, 1)));

    // truncating a smiley block:
    assertTrue(
        TBUtils.equal(new VComposition(new TextLine("   O "), new TextLine("~    ")), 
        new Truncated(aSmileyBlock, 5)));

    // truncating a right-justified block:
    assertTrue(TBUtils.equal(new TextLine("     "), new Truncated(aRightJustifiedBlock, 5)));

    // truncating a centered block:
    assertTrue(TBUtils.equal(new TextLine("    S"), new Truncated(aCenteredBlock, 5)));

    // truncating a horizontally-flipped block:
    assertTrue(TBUtils.equal(new TextLine("sratS"), new Truncated(aHorizontallyFlippedBlock, 5)));

    // truncating a vertically-flipped block:
    assertTrue(TBUtils.equal(new TextLine("Stars"), new Truncated(aVerticallyFlippedBlock, 5)));
  } // truncatedBlockCombinationTests()

  /**
   * Tests for empty truncated blocks (edge case)
   */
  @Test
  public void emptyTruncatedBlockTests() throws Exception {
    Truncated emptyTruncatedBlock = new Truncated(emptyLine, 7);
    assertEquals("       ", TBUtils.toString(emptyTruncatedBlock));
  } // emptyTruncatedBlockTests()

  // Horizontally Flipped Blocks

  /**
   * Tests to check that what is printed by the horizontally-flipped TextBlock is
   * the same as what is expected
   * to be printed.
   * TBUtils.toString(TextBlock) uses the row(int) method from TextBlocks,
   * so if the output from the former method is equal to the expected String,
   * then row(int) shows expected behavior (`row` works as a method)
   */
  @Test
  public void horizontallyFlippedBlockRowTests() throws Exception {
    HorizontallyFlipped hFlippedTextLine = new HorizontallyFlipped(line);
    HorizontallyFlipped hFlippedPalindrome = new HorizontallyFlipped(new TextLine("LeveL"));
    HorizontallyFlipped hFlippedBox = new HorizontallyFlipped(box);
    HorizontallyFlipped twiceHFlippedBlock = new HorizontallyFlipped(hFlippedBox);
    HorizontallyFlipped hFlippedLongBox = new HorizontallyFlipped(
        new HComposition(box, boxInABox));

    assertEquals("a horizontally-flipped line", "sratS", TBUtils.toString(hFlippedTextLine));
    assertEquals("horizontally flipping a palindrome word does nothing",
        "LeveL", TBUtils.toString(hFlippedPalindrome));
    assertEquals("horizontally flipped box",
        "+-----+\n" +
            "|sratS|\n" +
            "+-----+",
        TBUtils.toString(hFlippedBox));
    assertEquals("a box flipped horizontally twice is the original unflipped box",
        "+-----+\n" +
            "|Stars|\n" +
            "+-----+",
        TBUtils.toString(twiceHFlippedBlock));
    assertEquals("two horizontally-composed boxes flipped horizontally",
        "+-------++-----+\n" +
            "|+-----+||sratS|\n" +
            "||sratS||+-----+\n" +
            "|+-----+|       \n" +
            "+-------+       ",
        TBUtils.toString(hFlippedLongBox));
  } // horizontallyFlippedBlockRowTests()

  /**
   * Tests for confirming the width and height of horizontally-flipped TextBlocks.
   */
  @Test
  public void horizontallyFlippedBlockWidthHeightTests() throws Exception {
    HorizontallyFlipped hFlippedBox = new HorizontallyFlipped(box);

    assertEquals("width of a horizontally-flipped box stays the same", box.width(),
        hFlippedBox.width());
    assertEquals("height of a horizontally-flipped box stays the same", box.height(),
        hFlippedBox.height());
  } // horizontallyFlippedBlockWidthHeightTests()

  /**
   * Tests for proper horizontal flipping of other Text Block types and for
   * HorizontallyFlipped block types.
   */
  @Test
  public void horizontallyFlippedBlockCombinationTests() throws Exception {
    // horizontally-flipping a horizontally-flipped block:
    assertTrue(TBUtils.equal(new TextLine("Stars"), 
        new HorizontallyFlipped(aHorizontallyFlippedBlock)));

    // horizontally-flipping a truncated block:
    assertTrue(TBUtils.equal(new TextLine("atS"), new HorizontallyFlipped(aTruncatedBlock)));

    // horizontally-flipping a smiley block:
    assertTrue(TBUtils.equal(new VComposition(new TextLine("   O   sratS   O   "), 
        new TextLine("~        U        ~")),
        new HorizontallyFlipped(aSmileyBlock)));

    // horizontally-flipping a right-justified block:
    assertTrue(TBUtils.equal(new TextLine("sratS       "), 
        new HorizontallyFlipped(aRightJustifiedBlock)));

    // horizontally-flipping a centered block:
    assertTrue(TBUtils.equal(new TextLine("    sratS    "), 
        new HorizontallyFlipped(aCenteredBlock)));

    // horizontally-flipping a vertically-flipped block:
    assertTrue(TBUtils.equal(new TextLine("sratS"), 
        new HorizontallyFlipped(aVerticallyFlippedBlock)));
  } // horizontallyFlippedBlockCombinationTests()

  /**
   * Tests for empty horizontally-flipped blocks (edge case)
   */
  @Test
  public void emptyHorizontallyFlippedBlockTests() throws Exception {
    HorizontallyFlipped emptyHorizontallyFlippedBlock = new HorizontallyFlipped(emptyLine);
    assertEquals("", TBUtils.toString(emptyHorizontallyFlippedBlock));
  } // emptyHorizontallyFlippedBlockTests()

  // Vertically Flipped Blocks

  /**
   * Tests to check that what is printed by the vertically-flipped TextBlock is
   * the same as what is expected
   * to be printed.
   * TBUtils.toString(TextBlock) uses the row(int) method from TextBlocks,
   * so if the output from the former method is equal to the expected String,
   * then row(int) shows expected behavior (`row` works as a method)
   */
  @Test
  public void verticallyFlippedBlockRowTests() throws Exception {
    VerticallyFlipped vFlippedTextLine = new VerticallyFlipped(line);
    VerticallyFlipped shortVFlippedBox = new VerticallyFlipped(new BoxedBlock(line));
    VerticallyFlipped tallVFlippedBox = new VerticallyFlipped(
        new VComposition(new BoxedBlock(new TextLine("Thing ONE")), 
        new BoxedBlock(new TextLine("Thing TWO"))));
    VerticallyFlipped twiceVerticallyFlippedBlock = new VerticallyFlipped(
        new VerticallyFlipped(new VComposition(emptyBox, box)));

    assertEquals("a vertically-flipped line", "Stars", TBUtils.toString(vFlippedTextLine));
    assertEquals("vertically flipping a box of height 3 does nothing",
        "+-----+\n" +
            "|Stars|\n" +
            "+-----+",
        TBUtils.toString(shortVFlippedBox));
    assertEquals("vertically flipping a vertical composition of two different boxes",
        "+---------+\n" +
            "|Thing TWO|\n" +
            "+---------+\n" +
            "+---------+\n" +
            "|Thing ONE|\n" +
            "+---------+",
        TBUtils.toString(tallVFlippedBox));
    assertEquals("vertically flipping a vertical composition twice does nothing",
        "++     \n" +
            "||     \n" +
            "++     \n" +
            "+-----+\n" +
            "|Stars|\n" +
            "+-----+",
        TBUtils.toString(twiceVerticallyFlippedBlock));
  } // verticallyFlippedBlockRowTests()

  /**
   * Tests for confirming the width and height of vertically-flipped TextBlocks.
   */
  @Test
  public void verticallyFlippedBlockWidthHeightTests() throws Exception {
    VerticallyFlipped tallVFlippedBox = new VerticallyFlipped(
        new VComposition(new BoxedBlock(new TextLine("1")), new BoxedBlock(new TextLine("2"))));

    assertEquals("width of a vertically-flipped box stays the same", 3, tallVFlippedBox.width());
    assertEquals("height of a vertically-flipped box stays the same",
        3 + 3, tallVFlippedBox.height());
    // same as the added heights of its contents
  } // verticallyFlippedBlockWidthHeightTests()

  /**
   * Tests for proper vertical flipping of other Text Block types and for
   * VerticallyFlipped block types.
   */
  @Test
  public void verticallyFlippedBlockCombinationTests() throws Exception {
    // vertically-flipping a vertically-flipped block:
    assertTrue(TBUtils.equal(new TextLine("Stars"), 
        new VerticallyFlipped(aVerticallyFlippedBlock)));

    // vertically-flipping a horizontally-flipped block:
    assertTrue(TBUtils.equal(new TextLine("sratS"), 
        new VerticallyFlipped(aHorizontallyFlippedBlock)));

    // vertically-flipping a truncated block:
    assertTrue(TBUtils.equal(new TextLine("Sta"), new VerticallyFlipped(aTruncatedBlock)));

    // vertically-flipping a smiley block:
    assertTrue(TBUtils.equal(new VComposition(new TextLine("~        U        ~"), 
        new TextLine("   O   Stars   O   ")),
        new VerticallyFlipped(aSmileyBlock)));

    // vertically-flipping a right-justified block:
    assertTrue(TBUtils.equal(new TextLine("       Stars"), 
        new VerticallyFlipped(aRightJustifiedBlock)));

    // vertically-flipping a centered block:
    assertTrue(TBUtils.equal(new TextLine("    Stars    "), 
        new VerticallyFlipped(aCenteredBlock)));
  } // verticallyFlippedBlockCombinationTests()

  /**
   * Tests for empty vertically-flipped blocks (edge case)
   */
  @Test
  public void emptyVerticallyFlippedBlockTests() throws Exception {
    VerticallyFlipped emptyVerticallyFlippedBlock = new VerticallyFlipped(emptyLine);
    assertEquals("", TBUtils.toString(emptyVerticallyFlippedBlock));
  } // emptyVerticallyFlippedBlockTests()

  // equal, eqv, and eq Method Tests

  /**
   * Tests for confirming if equal method can discern text blocks that look the
   * same
   * when printed from those that do not.
   */
  @Test
  public void equalTests() {
    assertEquals("blocks that are identical in construction and looks", true,
        TBUtils.equal(sameBuildSameLooksObj1, sameBuildSameLooksObj2));
    assertEquals("blocks that are built differently but look the same", true,
        TBUtils.equal(diffBuildSameLooksObj1, diffBuildSameLooksObj2));

    assertEquals("blocks that are constructed the same way but look different", false,
        TBUtils.equal(sameBuildDiffLooksObj1, sameBuildDiffLooksObj2));
    assertEquals("blocks that are built differently and look different", false,
        TBUtils.equal(diffBuildDiffLooksObj1, diffBuildDiffLooksObj2));

    assertEquals("a block compared to itself", true,
        TBUtils.equal(hFlippedPalindrome, hFlippedPalindrome));
  } // equalTests()

  /**
   * Tests for confirming if eqv method can discern text blocks that are built
   * the same way from those that are not.
   */
  @Test
  public void eqvTests() {
    assertEquals("blocks that are constructed the same way but look different", true,
        TBUtils.eqv(sameBuildDiffLooksObj1, sameBuildDiffLooksObj2));
    assertEquals("blocks that are identical in construction and looks", true,
        TBUtils.eqv(sameBuildSameLooksObj1, sameBuildSameLooksObj2));

    assertEquals("blocks that are built differently but look the same", false,
        TBUtils.eqv(diffBuildSameLooksObj1, diffBuildSameLooksObj2));
    assertEquals("blocks that are built differently and look different", false,
        TBUtils.eqv(diffBuildDiffLooksObj1, diffBuildDiffLooksObj2));

    assertEquals("a block compared to itself", true,
        TBUtils.eqv(hFlippedPalindrome, hFlippedPalindrome));
  } // eqvTest()

  /**
   * Tests for confirming if eq method can discern text blocks that occupy
   * the same space in memory from those that do not.
   */
  @Test
  public void eqTests() {
    assertEquals("blocks that are constructed the same way but look different", false,
        TBUtils.eq(sameBuildDiffLooksObj1, sameBuildDiffLooksObj2));
    assertEquals("blocks that are identical in construction and looks", false,
        TBUtils.eq(sameBuildSameLooksObj1, sameBuildSameLooksObj2));
    assertEquals("blocks that are built differently but look the same", false,
        TBUtils.eq(diffBuildSameLooksObj1, diffBuildSameLooksObj2));
    assertEquals("blocks that are built differently and look different", false,
        TBUtils.eq(diffBuildDiffLooksObj1, diffBuildDiffLooksObj2));

    assertEquals("a block compared to itself", true,
        TBUtils.eq(hFlippedPalindrome, hFlippedPalindrome));
    assertEquals("a block compared to a copy of itself but in a different memory location", false,
        TBUtils.eq(hFlippedPalindrome, copyOfhFlippedPalindrome));
  } // eqTests()
} // class TextBlockTests