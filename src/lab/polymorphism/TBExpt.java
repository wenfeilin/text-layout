package lab.polymorphism;


import java.io.PrintWriter;

import proj.Centered;
import proj.HorizontallyFlipped;
import proj.RightJustified;
import proj.SmileyBlock;
import proj.Truncated;
import proj.VerticallyFlipped;

/**
 * A series of experiments with the text block layout classes.
 * 
 * @author Samuel A. Rebelsky
 * @version 1.3 of September 2019
 */
public class TBExpt {
  // +------+--------------------------------------------------------------
  // | Main |
  // +------+

  public static void main(String[] args) throws Exception {
    // Prepare for input and output
    PrintWriter pen = new PrintWriter(System.out, true);

    // Create a block to use
    TextBlock block = new TextLine("Hello");
    TextBlock secondBlock = new TextLine("Hello");
    TextBlock block1 = new BoxedBlock(new TextLine("Wenfei"));
    TextBlock block2 = new HComposition(new BoxedBlock(new TextLine("Wenfei")), new TextLine ("Lin"));
    TextBlock block3 = new VComposition(new TextLine("Potato"), new BoxedBlock(new TextLine("Wenfei")));
    TextBlock blockTrunc = new Truncated(block, 2);
    TextBlock block1Trunc = new Truncated(block1, 2);
    TextBlock block2Trunc = new Truncated(block2, 2);
    TextBlock block3Trunc = new Truncated(block3, 2);
    TextBlock blockCentered = new Centered(block, 10);
    TextBlock block1Centered = new Centered(block1, 15);
    TextBlock block2Centered = new Centered(block2, 20);
    TextBlock block3Centered = new Centered(block3, 30);
    TextBlock blockRightJustified = new RightJustified(block, 10);
    TextBlock block1RightJustified = new RightJustified(block1, 15);
    TextBlock block2RightJustified = new RightJustified(block2, 20);
    TextBlock block3RightJustified = new RightJustified(block3, 30);
    TextBlock blockVFlipped = new VerticallyFlipped(block);
    TextBlock block1VFlipped = new VerticallyFlipped(block1);
    TextBlock block2VFlipped = new VerticallyFlipped(block2);
    TextBlock block3VFlipped = new VerticallyFlipped(block3);
    TextBlock blockHFlipped = new HorizontallyFlipped(block);
    TextBlock block1HFlipped = new HorizontallyFlipped(block1);
    TextBlock block2HFlipped = new HorizontallyFlipped(block2);
    TextBlock block3HFlipped = new HorizontallyFlipped(block3);

    // Print out the block
    TBUtils.print(pen, block);
    TBUtils.print(pen, block1);
    TBUtils.print(pen, block2);
    TBUtils.print(pen, block3);
    TBUtils.print(pen, blockTrunc);
    TBUtils.print(pen, block1Trunc);
    TBUtils.print(pen, block2Trunc);
    TBUtils.print(pen, block3Trunc);
    TBUtils.print(pen, blockCentered);
    TBUtils.print(pen, block1Centered);
    TBUtils.print(pen, block2Centered);
    TBUtils.print(pen, block3Centered);
    TBUtils.print(pen, blockRightJustified);
    TBUtils.print(pen, block1RightJustified);
    TBUtils.print(pen, block2RightJustified);
    TBUtils.print(pen, block3RightJustified);
    TBUtils.print(pen, blockVFlipped);
    TBUtils.print(pen, block1VFlipped);
    TBUtils.print(pen, block2VFlipped);
    TBUtils.print(pen, block3VFlipped);
    TBUtils.print(pen, blockHFlipped);
    TBUtils.print(pen, block1HFlipped);
    TBUtils.print(pen, block2HFlipped);
    TBUtils.print(pen, block3HFlipped);
    pen.println(TBUtils.equal(block, block1));

    pen.println(TBUtils.eq(block, block));
    pen.println(TBUtils.eq(block, secondBlock));

    Truncated thingOne = new Truncated(new TextLine("thing1"), 4);
    Truncated thingTwo = new Truncated(new TextLine("thing2"), 4);
    Truncated notThingTwo = new Truncated(new Centered (new TextLine("thing1"), 6), 4);

    TBUtils.print(pen, thingOne);
    TBUtils.print(pen, thingTwo);
    TBUtils.print(pen, notThingTwo);
    
    pen.println(thingOne.eqv(thingTwo)); //true
    pen.println(thingOne.eqv(notThingTwo)); //false

    VComposition VCompOne = new VComposition(new TextLine("thing1"), new BoxedBlock(new TextLine("yas")));
    VComposition VCompTwo = new VComposition(new TextLine("blah blah"), new BoxedBlock(new TextLine("not gon match")));
    VComposition notVCompTwo = new VComposition(new TextLine("thing1"), new BoxedBlock(new Centered (new TextLine("yas"), 3)));

    pen.println(VCompOne.eqv(VCompTwo)); //true
    pen.println(VCompOne.eqv(notVCompTwo)); //false
    pen.println(TBUtils.eqv(VCompOne, VCompTwo));

    SmileyBlock firstSmile = new SmileyBlock(new TextLine("Stars"));
    System.out.println(TBUtils.toString(block1));
    String ye = "+------+\n" + //
                "|Wenfei|\n" + //
                "+------+";
    System.out.println(TBUtils.toString(block1).equals(ye));
    System.out.println("+------+\n" + //
        "|Wenfei|\n" + //
        "+------+");
    TBUtils.print(pen, firstSmile);

    SmileyBlock smileyLine = new SmileyBlock(new TextLine(" "));
    TBUtils.print(pen, smileyLine);

    SmileyBlock tallSmiley = new SmileyBlock(new VComposition(new BoxedBlock(new TextLine("ABOVE THE")), (new TextLine ("Stars"))));
    TBUtils.print(pen, tallSmiley);
    String hey = "|ABOVE THE|";
    pen.println(hey.length());

    // Clean up after ourselves.
    pen.close();
  } // main(String[])

} // class TBExpt
