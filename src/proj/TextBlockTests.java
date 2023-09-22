package proj;

import lab.polymorphism.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for TextBlock methods
 * 
 * @author Wenfei Lin
 */
public class TextBlockTests {
  TextLine line, noLine;
  BoxedBlock box, boxInABox, emptyBox;
  HComposition leftRightComp;
  VComposition topBottomComp, diffBuildSameLooks1;
  Truncated truncatedNormally, truncatedBox, notTruncated, truncatedButExpanded;
  Centered centeredNormally, notCentered, centeredUnevenly, centeredButTruncated;
  RightJustified rightJustifiedNormally, sameBuildDiffLooks1, sameBuildDiffLooks2, notRightJustified, rightJustifiedButTruncated;
  HorizontallyFlipped hFlippedLine, sameBuildSameLooks1, sameBuildSameLooks2, hFlippedPalindrome, hFlippedBox, hFlippedTwice, hFlippedLongBox;
  VerticallyFlipped vFlippedLine, diffBuildSameLooks2, vFlippedShortBox, vFlippedTallBox, vFlippedTwice;
  SmileyBlock smileyLine, diffBuildDiffLooks1, diffBuildDiffLooks2, tallSmiley, smileyMouthInMiddle, smileyMouthNotInMiddle, justSmiley, longBlockSmiley, shortBlockSmiley;

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
    tallSmiley = new SmileyBlock(new VComposition(new BoxedBlock(new TextLine("ABOVE THE")), line));
    vFlippedShortBox = new VerticallyFlipped(new BoxedBlock(line));
    vFlippedTallBox = new VerticallyFlipped(new VComposition(new BoxedBlock(new TextLine("Thing ONE")), new BoxedBlock(new TextLine("Thing TWO"))));
    vFlippedTwice = new VerticallyFlipped(new VerticallyFlipped(new VComposition(emptyBox, box)));
    hFlippedPalindrome = new HorizontallyFlipped(new TextLine("LeveL"));
    hFlippedBox = new HorizontallyFlipped(box);
    hFlippedTwice = new HorizontallyFlipped(hFlippedBox);
    hFlippedLongBox = new HorizontallyFlipped(new HComposition(box, boxInABox)); // two Hcomp boxes
    truncatedBox = new Truncated(box, truncatedLineWidth);
    notCentered = new Centered(line, sameLineWidth);
    centeredUnevenly = new Centered(line, sameLineWidth + 1);
    centeredButTruncated = new Centered(line, truncatedLineWidth);
    notTruncated = new Truncated(line, sameLineWidth);
    truncatedButExpanded = new Truncated(line, sameLineWidth + 1);
    notRightJustified = new RightJustified(line, sameLineWidth);
    rightJustifiedButTruncated = new RightJustified(line, truncatedLineWidth);
    smileyMouthInMiddle = new SmileyBlock(line);
    smileyMouthNotInMiddle = new SmileyBlock(new TextLine("Uneven"));
    justSmiley = new SmileyBlock(noLine);
    longBlockSmiley = new SmileyBlock(new TextLine("Loooooooooooong text"));
    shortBlockSmiley = new SmileyBlock(new TextLine("short"));


    // also need to test for HComp and VComp





    // For both text blocks for comparing with equal (same lines) and 
    // text blocks for comparing with eqv (same construction):

    // Built the same but looks diff
    sameBuildDiffLooks1 = new RightJustified(new BoxedBlock(new TextLine ("potatoes")), rightJustifiedLineWidth);
    sameBuildDiffLooks2 = new RightJustified(new BoxedBlock(new TextLine ("fries")), rightJustifiedLineWidth);
    
    // Built the same and looks the same
    sameBuildSameLooks1 = new HorizontallyFlipped (new SmileyBlock(new BoxedBlock(new TextLine("Reading backwards"))));
    sameBuildSameLooks2 = new HorizontallyFlipped (new SmileyBlock(new BoxedBlock(new TextLine("Reading backwards"))));

    // Built diff but looks the same
    diffBuildSameLooks1 = new VComposition(new BoxedBlock(line), new TextLine("in the sky"));
    diffBuildSameLooks2 = new VerticallyFlipped (new VerticallyFlipped(new VComposition(new BoxedBlock(line), new TextLine("in the sky"))));

    // Built diff and looks diff
    diffBuildDiffLooks1 = new SmileyBlock(new BoxedBlock(new SmileyBlock(new TextLine(" ~   O U O   ~"))));
    diffBuildDiffLooks1 = new SmileyBlock(new SmileyBlock(new TextLine("T n T")));

    // ones built by HComp and VComp for eqv b/c those two work differently than other text blocks 
    // (have two text blocks to consider inside it instead of one)



    // Text blocks for comparing with eq (same memory):

    // A text block that will be inputted as both params for eq (occupy same space)
    // Identical text blocks but two different objects (occupy diff spaces)


  } // setUp()

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

    assertEquals("a horizontal composition of two same lines", "Little Stars", TBUtils.toString(leftRightComp));
    assertEquals("a vertical composition of two same lines", 
                 "Stars\n" + //
                 "Stars", TBUtils.toString(topBottomComp));

    assertEquals("a line truncated to width of three", "Sta", TBUtils.toString(truncatedNormally));
    assertEquals("a line within a width of 17", "      Stars      ", TBUtils.toString(centeredNormally));
    assertEquals("a line right-justified within a width of 17", "            Stars", TBUtils.toString(rightJustifiedNormally));

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

    assertEquals("centering with the same width does nothing", "Stars", TBUtils.toString(notCentered));
    assertEquals("centering is uneven due to new width", " Stars", TBUtils.toString(centeredUnevenly));
    assertEquals("centering with width less than TextBlock's width truncates block", "Sta", TBUtils.toString(centeredButTruncated));

    assertEquals("truncating with the same width does nothing", "Stars", TBUtils.toString(notTruncated));
    assertEquals("truncating with a larger width expands the text block", "Stars ", TBUtils.toString(truncatedButExpanded));
    assertEquals("", 
                 "+--\n" + //
                 "|St\n" + //
                 "+--", TBUtils.toString(truncatedBox));

    assertEquals("right-justifying with the same width does nothing", "Stars", TBUtils.toString(notRightJustified));
    assertEquals("", "Sta", TBUtils.toString(rightJustifiedButTruncated));

    assertEquals("mouth is centered under the text block", 
                 "   O   Stars   O   \n" + //
                 "~        U        ~", TBUtils.toString(smileyMouthInMiddle));
    assertEquals("mouth is not centered under the text block", 
                 "   O   Uneven   O   \n" + //
                 "~         U        ~", TBUtils.toString(smileyMouthNotInMiddle));
    assertEquals("", 
                 "   O O   \n" + //
                 "~   U   ~", TBUtils.toString(justSmiley));
    assertEquals("example of spacing increasing in creation of smileyBlock when given a longer block", 
                 "   O          Loooooooooooong text          O   \n" + //
                 "~                       U                      ~", TBUtils.toString(longBlockSmiley));
    assertEquals("example of spacing decreasing in creation of smileyBlock when given a shorter block", 
                 "   O   short   O   \n" + //
                 "~        U        ~", TBUtils.toString(shortBlockSmiley));
    assertEquals("smiley face on a tall vertical composition", 
                 "          +---------+          \n" + //
                 "          |ABOVE THE|          \n" + //
                 "   O      +---------+      O   \n" + //
                 "          Stars                \n" + //
                 "~              U              ~", TBUtils.toString(tallSmiley));
  } // rowTests()

  @Test
  public void widthTests() {

    
    //assertEquals();

  }

  @Test
  public void heightTests() {
    // assertEquals("height of a line", 1, line.height());
    // assertEquals("height of a boxed line", 1 + 2, box.height());
  }

  @Test
  public void equalTests() {

  }

  @Test
  public void eqvTests() {

  }

  @Test
  public void eqTests() {

  }

  @Test
  public void getContentsTests() {

  }
  // } // 
}
