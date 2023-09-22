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
  TextLine line, noLine;
  BoxedBlock box, boxInABox, emptyBox;
  HComposition leftRightComp;
  VComposition topBottomComp, diffBuildSameLooks1, sameBuildDiffLooks1, sameBuildDiffLooks2;
  Truncated truncatedNormally, truncatedBox, notTruncated, truncatedButExpanded, 
            truncatedEverything;
  Centered centeredNormally, notCentered, centeredUnevenly, centeredButTruncated, 
            centeredTruncatedEverything;
  RightJustified rightJustifiedNormally, notRightJustified, rightJustifiedButTruncated, 
                  rightJustifiedTruncatedEverything;
  HorizontallyFlipped hFlippedLine, sameBuildSameLooks1, sameBuildSameLooks2, hFlippedPalindrome,
                      hFlippedBox, hFlippedTwice, hFlippedLongBox, copyOfhFlippedPalindrome;
  VerticallyFlipped vFlippedLine, diffBuildSameLooks2, vFlippedShortBox, vFlippedTallBox, 
                    vFlippedTwice;
  SmileyBlock smileyLine, diffBuildDiffLooks1, diffBuildDiffLooks2, tallSmiley, 
              smileyMouthInMiddle, smileyMouthNotInMiddle, justSmiley, longBlockSmiley, 
              shortBlockSmiley;

  /**
   * The setup of TextBlock objects to be used in the tests.
   * 
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    int centeredLineWidth = 17;
    int rightJustifiedLineWidth = 17;
    int truncatedLineWidth = 3;
    int sameLineWidth = 5; // width of "Stars" line

    // TextBoxes composed of TextLines only:
    line = new TextLine("Stars");
    box = new BoxedBlock(line);
    leftRightComp = new HComposition(new TextLine("Little "), line);
    topBottomComp = new VComposition(line, line);
    truncatedNormally = new Truncated(line, truncatedLineWidth);
    centeredNormally = new Centered(line, centeredLineWidth);
    rightJustifiedNormally = new RightJustified(line, rightJustifiedLineWidth);
    hFlippedLine = new HorizontallyFlipped(line);
    vFlippedLine = new VerticallyFlipped(line);
    smileyLine = new SmileyBlock(line);

    // Some combinations of TextBox types + showcase various cases + edge cases:
    noLine = new TextLine("");
    emptyBox = new BoxedBlock(noLine);
    boxInABox = new BoxedBlock(new BoxedBlock(line));
    tallSmiley = new SmileyBlock(new VComposition(new BoxedBlock
        (new TextLine("ABOVE THE")), line));
    vFlippedShortBox = new VerticallyFlipped(new BoxedBlock(line));
    vFlippedTallBox = new VerticallyFlipped(new VComposition(new BoxedBlock
        (new TextLine("Thing ONE")), new BoxedBlock(new TextLine("Thing TWO"))));
    vFlippedTwice = new VerticallyFlipped(new VerticallyFlipped(new VComposition(emptyBox, box)));
    hFlippedPalindrome = new HorizontallyFlipped(new TextLine("LeveL"));
    hFlippedBox = new HorizontallyFlipped(box);
    hFlippedTwice = new HorizontallyFlipped(hFlippedBox);
    hFlippedLongBox = new HorizontallyFlipped(new HComposition(box, boxInABox)); 
    truncatedBox = new Truncated(box, truncatedLineWidth);
    truncatedEverything = new Truncated(line, 0);
    notCentered = new Centered(line, sameLineWidth);
    centeredUnevenly = new Centered(line, sameLineWidth + 1);
    centeredButTruncated = new Centered(line, truncatedLineWidth);
    centeredTruncatedEverything = new Centered(line, 0);
    notTruncated = new Truncated(line, sameLineWidth);
    truncatedButExpanded = new Truncated(line, sameLineWidth + 1);
    notRightJustified = new RightJustified(line, sameLineWidth);
    rightJustifiedButTruncated = new RightJustified(line, truncatedLineWidth);
    rightJustifiedTruncatedEverything = new RightJustified(line, 0);
    smileyMouthInMiddle = new SmileyBlock(line);
    smileyMouthNotInMiddle = new SmileyBlock(new TextLine("Uneven"));
    justSmiley = new SmileyBlock(noLine);
    longBlockSmiley = new SmileyBlock(new TextLine("Loooooooooooong text"));
    shortBlockSmiley = new SmileyBlock(new TextLine("short"));

    // Built the same but looks diff
    sameBuildDiffLooks1 = new VComposition(new RightJustified(new BoxedBlock
        (new TextLine("potatoes")), rightJustifiedLineWidth), emptyBox);
    sameBuildDiffLooks2 = new VComposition(new RightJustified(new BoxedBlock
        (new TextLine("fries")), rightJustifiedLineWidth), emptyBox);
    
    // Built the same and looks the same
    sameBuildSameLooks1 = new HorizontallyFlipped(new HComposition(new SmileyBlock(new BoxedBlock
        (new TextLine("Reading backwards"))), new TextLine(":D")));
    sameBuildSameLooks2 = new HorizontallyFlipped(new HComposition(new SmileyBlock(new BoxedBlock
        (new TextLine("Reading backwards"))), new TextLine(":D")));

    // Built diff but looks the same
    diffBuildSameLooks1 = new VComposition(new BoxedBlock(line), 
                                            new TextLine("in the sky"));
    diffBuildSameLooks2 = new VerticallyFlipped(new VerticallyFlipped
        (new VComposition(new BoxedBlock(line), new TextLine("in the sky"))));

    // Built diff and looks diff
    diffBuildDiffLooks1 = new SmileyBlock(new BoxedBlock(new SmileyBlock
        (new TextLine(" ~   O U O   ~"))));
    diffBuildDiffLooks2 = new SmileyBlock(new SmileyBlock(new TextLine("T n T")));


    // Text blocks for comparing with eq (same memory):

    // A text block that will be inputted as both params for eq (occupy same space)
    // Identical text blocks but two different objects (occupy diff spaces):
    copyOfhFlippedPalindrome = new HorizontallyFlipped(new TextLine("LeveL"));
  } // setUp()

  /**
   * Tests to check that what is printed by the TextBlock is the same as what is expected 
   * to be printed.
   * 
   * @throws Exception
   */
  @Test
  public void rowTests() throws Exception {
    // TBUtils.toString(TextBlock) uses the row(int) method from TextBlocks,
    // so if the output from the former method is equal to the expected String, 
    // then row(int) shows expected behavior (`row` works as a method)

    // Outputs of text boxes composed of TextLines only:
    assertEquals("a single line", "Stars", TBUtils.toString(line));
    assertEquals("a box with a line inside", 
                "+-----+\n" + //
                "|Stars|\n" + //
                "+-----+", 
                 TBUtils.toString(box));

    assertEquals("a horizontal composition of two same lines", "Little Stars", 
                  TBUtils.toString(leftRightComp));
    assertEquals("a vertical composition of two same lines", 
                 "Stars\n" + //
                 "Stars", TBUtils.toString(topBottomComp));

    assertEquals("a line truncated to width of three", "Sta", TBUtils.toString(truncatedNormally));
    assertEquals("a line within a width of 17", "      Stars      ", 
                  TBUtils.toString(centeredNormally));
    assertEquals("a line right-justified within a width of 17", "            Stars", 
                  TBUtils.toString(rightJustifiedNormally));

    assertEquals("a horizontally-flipped line", "sratS", TBUtils.toString(hFlippedLine));
    assertEquals("a vertically-flipped line", "Stars", TBUtils.toString(vFlippedLine));

    assertEquals("a line with a smiley face on it", 
                 "   O   Stars   O   \n" + //
                 "~        U        ~", TBUtils.toString(smileyLine));


    // Outputs of text boxes made from combinations of TextBox types + showing special behavior:
    assertEquals("line of text with nothing", "", TBUtils.toString(noLine));
    assertEquals("box border only", 
                 "++\n" + //
                 "||\n" + //
                 "++", TBUtils.toString(emptyBox));
    assertEquals("a box in a box",
                 "+-------+\n" + //
                 "|+-----+|\n" + //
                 "||Stars||\n" + //
                 "|+-----+|\n" + //
                 "+-------+", TBUtils.toString(boxInABox));

    assertEquals("vertically flipping a box of height 3 does nothing", 
                 "+-----+\n" + //
                 "|Stars|\n" + //
                 "+-----+", TBUtils.toString(vFlippedShortBox));
    assertEquals("vertically flipping a vertical composition of two different boxes", 
                 "+---------+\n" + //
                 "|Thing TWO|\n" + //
                 "+---------+\n" + //
                 "+---------+\n" + //
                 "|Thing ONE|\n" + //
                 "+---------+", TBUtils.toString(vFlippedTallBox));
    assertEquals("vertically flipping a vertical composition twice does nothing", 
                 "++     \n" + //
                 "||     \n" + //
                 "++     \n" + //
                 "+-----+\n" + //
                 "|Stars|\n" + //
                 "+-----+", TBUtils.toString(vFlippedTwice));

    assertEquals("horizontally flipping a palindrome word does nothing", 
                 "LeveL", TBUtils.toString(hFlippedPalindrome));
    assertEquals("horizontally flipped box",
                 "+-----+\n" + //
                 "|sratS|\n" + //
                 "+-----+", TBUtils.toString(hFlippedBox));
    assertEquals("a box flipped horizontally twice is the original unflipped box", 
                 "+-----+\n" + //
                 "|Stars|\n" + //
                 "+-----+", TBUtils.toString(hFlippedTwice));
    assertEquals("two horizontally-composed boxes flipped horizontally", 
                 "+-------++-----+\n" + //
                 "|+-----+||sratS|\n" + //
                 "||sratS||+-----+\n" + //
                 "|+-----+|       \n" + //
                 "+-------+       ", TBUtils.toString(hFlippedLongBox));

    assertEquals("centering with the same width does nothing", "Stars", 
                  TBUtils.toString(notCentered));
    assertEquals("centering is uneven due to new width", " Stars",  
                  TBUtils.toString(centeredUnevenly));
    assertEquals("centering with width less than TextBlock's width truncates block", "Sta", 
                  TBUtils.toString(centeredButTruncated));
    assertEquals("centering with new width of 0", "", 
                  TBUtils.toString(centeredTruncatedEverything));

    assertEquals("truncating with the same width does nothing", "Stars", 
                  TBUtils.toString(notTruncated));
    assertEquals("truncating with a larger width expands the text block", "Stars ", 
                  TBUtils.toString(truncatedButExpanded));
    assertEquals("", 
                 "+--\n" + //
                 "|St\n" + //
                 "+--", TBUtils.toString(truncatedBox));
    assertEquals("truncating with new width of 0", "", TBUtils.toString(truncatedEverything));

    assertEquals("right-justifying with the same width does nothing", "Stars", 
                  TBUtils.toString(notRightJustified));
    assertEquals("", "Sta", TBUtils.toString(rightJustifiedButTruncated));
    assertEquals("right-justifying with new width of 0", "", 
                  TBUtils.toString(rightJustifiedTruncatedEverything));

    assertEquals("mouth is centered under the text block", 
                 "   O   Stars   O   \n" + //
                 "~        U        ~", TBUtils.toString(smileyMouthInMiddle));
    assertEquals("mouth is not centered under the text block", 
                 "   O   Uneven   O   \n" + //
                 "~         U        ~", TBUtils.toString(smileyMouthNotInMiddle));
    assertEquals("", 
                 "   O O   \n" + //
                 "~   U   ~", TBUtils.toString(justSmiley));
    assertEquals("example of spacing increasing in creation of smileyBlock" + //
                "when given a longer block",
                 "   O          Loooooooooooong text          O   \n" + //
                 "~                       U                      ~", 
                TBUtils.toString(longBlockSmiley));
    assertEquals("example of spacing decreasing in creation of smileyBlock" + // 
                "when given a shorter block", 
                 "   O   short   O   \n" + //
                 "~        U        ~", TBUtils.toString(shortBlockSmiley));
    assertEquals("smiley face on a tall vertical composition", 
                 "          +---------+          \n" + //
                 "          |ABOVE THE|          \n" + //
                 "   O      +---------+      O   \n" + //
                 "          Stars                \n" + //
                 "~              U              ~", TBUtils.toString(tallSmiley));
  } // rowTests()

  /**
   * Tests for confirming the width of TextBlocks.
   */
  @Test
  public void widthTests() {
    assertEquals("height of line with no characters", 0, noLine.width());
    assertEquals("width of a line", 5, line.width());
    assertEquals("width of a truncated block is the new width", 3, truncatedNormally.width());
    assertEquals("width of a centered block is the new width", 17, centeredNormally.width());
    assertEquals("width of a right-justified block is the new width", 17, 
                  rightJustifiedNormally.width());

    assertEquals("width of an empty box", 2, emptyBox.width());
    assertEquals("width of a boxed line", line.width() + 2, box.width());
    assertEquals("width of a boxed line", line.width() + 2 + 2, boxInABox.width());
    assertEquals("width of a horizontally-flipped box stays the same", box.width(), 
                  hFlippedBox.width());
    assertEquals("width of a vertically-flipped box stays the same", 7, vFlippedTwice.width()); 

    assertEquals("width of just a smiley face", 9, justSmiley.width());
    assertEquals("width of line with a smiley face", 19, smileyLine.width());
    assertEquals("width of tall block with a smiley face", 31, tallSmiley.width());
  } // widthTests()

  /**
   * Tests for confirming the height of TextBlocks.
   */
  @Test
  public void heightTests() {
    assertEquals("height of line with no characters", 1, noLine.height());
    assertEquals("height of a line", 1, line.height());
    assertEquals("height of a truncated block stays the same", line.height(), 
                  truncatedNormally.height());
    assertEquals("height of a centered block stays the same", line.height(), 
                  centeredNormally.height());
    assertEquals("height of a right-justified block stays the same", line.height(), 
                  rightJustifiedNormally.height());

    assertEquals("height of an empty box", 3, emptyBox.height());
    assertEquals("height of a boxed line", 3, box.height());
    assertEquals("height of a boxed line", 5, boxInABox.height());
    assertEquals("height of a horizontally-flipped box stays the same", box.height(), 
                  hFlippedBox.height());
    assertEquals("height of a vertically-flipped box stays the same", 
                  emptyBox.height() + box.height(), vFlippedTwice.height()); 
                  // same as the added heights of its contents

    assertEquals("height of line with a smiley face", 2, smileyLine.height());
    assertEquals("height of tall block with a smiley face", 5, tallSmiley.height());
  } // heightTests()

  /**
   * Tests for confirming if equal method can discern text blocks that look the same 
   * when printed from those that do not.
   */
  @Test
  public void equalTests() {
    assertEquals("blocks that are identical in construction and looks", true, 
                  TBUtils.equal(sameBuildSameLooks1, sameBuildSameLooks2));
    assertEquals("blocks that are built differently but look the same", true, 
                  TBUtils.equal(diffBuildSameLooks1, diffBuildSameLooks2));

    assertEquals("blocks that are constructed the same way but look different", false, 
                  TBUtils.equal(sameBuildDiffLooks1, sameBuildDiffLooks2));
    assertEquals("blocks that are built differently and look different", false, 
                  TBUtils.equal(diffBuildDiffLooks1, diffBuildDiffLooks2));

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
                  TBUtils.eqv(sameBuildDiffLooks1, sameBuildDiffLooks2));
    assertEquals("blocks that are identical in construction and looks", true, 
                  TBUtils.eqv(sameBuildSameLooks1, sameBuildSameLooks2));

    assertEquals("blocks that are built differently but look the same", false, 
                  TBUtils.eqv(diffBuildSameLooks1, diffBuildSameLooks2));
    assertEquals("blocks that are built differently and look different", false, 
                  TBUtils.eqv(diffBuildDiffLooks1, diffBuildDiffLooks2));

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
                  TBUtils.eq(sameBuildDiffLooks1, sameBuildDiffLooks2));
    assertEquals("blocks that are identical in construction and looks", false, 
                  TBUtils.eq(sameBuildSameLooks1, sameBuildSameLooks2));
    assertEquals("blocks that are built differently but look the same", false, 
                  TBUtils.eq(diffBuildSameLooks1, diffBuildSameLooks2));
    assertEquals("blocks that are built differently and look different", false, 
                  TBUtils.eq(diffBuildDiffLooks1, diffBuildDiffLooks2));

    assertEquals("a block compared to itself", true, 
                  TBUtils.eq(hFlippedPalindrome, hFlippedPalindrome)); 
    assertEquals("a block compared to a copy of itself but in a different memory location", false,
                  TBUtils.eq(hFlippedPalindrome, copyOfhFlippedPalindrome)); 
  } // eqTests()
} // class TextBlockTests
