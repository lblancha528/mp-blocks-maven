package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Grid;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.NewBlock;
import edu.grinnell.csc207.blocks.Boxed;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.HFlip;
import edu.grinnell.csc207.blocks.VComp;
import edu.grinnell.csc207.blocks.VFlip;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.Surrounded;

/**
 * Tests of the new block.
 */
public class TestNewBlock {
  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * Some tests for NewBlock given rectangle blocks.
   */
  @Test
  public void newBlockWithRectTests() {
    try {
      Rect blockA = new Rect('a', 3, 4);
      Rect blockB = new Rect('b', 4, 5);
      Rect blockC = new Rect('c', 2, 2);
      Rect blockD = new Rect('d', 3, 2);
      NewBlock aAndB = new NewBlock(blockA, blockB);
      NewBlock bAndD = new NewBlock(blockB, blockD);
      NewBlock cAndD = new NewBlock(blockC, blockD);

      assertEquals(4, aAndB.width(), "ab width");
      assertEquals(4, bAndD.width(), "bd width");
      assertEquals(3, cAndD.width(), "bd width");
      assertEquals(8, aAndB.height(), "SHSA height");
      assertEquals(4, bAndD.height(), "PESA height");
      assertEquals(4, cAndD.height(), "PESA height");

      assertEquals("""
                    aaa
                    bbbb
                    aaa
                    bbbb
                    aaa
                    bbbb
                    aaa
                    bbbb
                    """
      , TestUtils.toString(aAndB), "a and b, different heights");
      assertEquals("""
                    bbbb
                    ddd
                    bbbb
                    ddd
                    """
      , TestUtils.toString(bAndD), "b and d, different heights");
      assertEquals("""
                    cc
                    ddd
                    cc
                    ddd
                    """
      , TestUtils.toString(cAndD), "c and d, same heights");
    } catch (Exception e) {
      e.printStackTrace();
    } // try/catch
  } // newBlockWithRectTests()

  /**
   * Some tests for NewBlock given grid blocks.
   */
  @Test
  public void newBlockWithGridTests() {
    try {
      Line starAndHash = new Line("*#");
      Line slashAndAt = new Line("/@");
      Line plusAndEquals = new Line("+=");
      Grid starHash = new Grid(starAndHash, 4, 5);
      Grid slashAt = new Grid(slashAndAt, 6, 3);
      Grid plusEquals = new Grid(plusAndEquals, 5, 1);
      NewBlock SHSAMix = new NewBlock(starHash, slashAt);
      NewBlock PESAMix = new NewBlock(plusEquals, slashAt);

      assertEquals(12, SHSAMix.width(), "SHSA width");
      assertEquals(12, PESAMix.width(), "PESA width");
      assertEquals(6, SHSAMix.height(), "SHSA height");
      assertEquals(2, PESAMix.height(), "PESA height");

      assertEquals("""
                    *#*#*#*#
                    /@/@/@/@/@/@
                    *#*#*#*#
                    /@/@/@/@/@/@
                    *#*#*#*#
                    /@/@/@/@/@/@                    
                    """
      , TestUtils.toString(SHSAMix), "star, hash, slash, at");
      assertEquals("""
                    +=+=+=+=+=
                    /@/@/@/@/@/@                  
                    """
      , TestUtils.toString(PESAMix), "block with one row");

    } catch (Exception e){
      e.printStackTrace();
    } // try/catch
  } // newBlockWithGridTests()

  /**
   * Some tests for NewBlock given Boxed blocks.
   */
  @Test
  public void newBlockWithBoxedTests() {
    try {
      Grid ampAndPercGrid = new Grid(new Line("&%"), 3, 4);
      Boxed emptyBox = new Boxed(new Line(""));
      Boxed helloBox = new Boxed(new Line("hello"));
      Boxed gridBox = new Boxed(ampAndPercGrid);
      Boxed doubleBoxed = new Boxed(helloBox);
      NewBlock emptyAndHello = new NewBlock(emptyBox, helloBox);
      NewBlock helloAndGrid = new NewBlock(helloBox, gridBox);
      NewBlock helloAndDouble = new NewBlock(helloBox, doubleBoxed);

      assertEquals(7, emptyAndHello.width(), "empty/hello width");
      assertEquals(8, helloAndGrid.width(), "hello/grid width");
      assertEquals(9, helloAndDouble.width(), "hello/double width");
      assertEquals(6, emptyAndHello.height(), "empty/hello height");
      assertEquals(6, helloAndGrid.height(), "hello/grid height");
      assertEquals(6, helloAndDouble.height(), "hello/double height");
      
      assertEquals("""
                    /\\
                    /-----\\
                    \\/
                    |hello|                  
                    """
      , TestUtils.toString(emptyAndHello), "emptyBox and helloBox");
      assertEquals("""
                    /-----\\
                    /------\\
                    |hello|
                    |&%&%&%|
                    \\-----/
                    |&%&%&%|                  
                    """
      , TestUtils.toString(helloAndGrid), "helloBox and gridBox");
      assertEquals("""
                    /-----\\
                    /-------\\
                    |hello|
                    |/-----\\|
                    \\-----/
                    ||hello||                  
                    """
      , TestUtils.toString(helloAndDouble), "helloBox and doubleBoxed");
    } catch (Exception e) {
      e.printStackTrace();
    }
  } // newBlockWithBoxedTests()

  /**
   * Some tests for NewBlock given VComp and Hcomp blocks.
   */
  @Test
  public void newBlockWithCompTests() {
    try {
      Line helloL = new Line("hello");
      Line goodbyeL = new Line("goodbye");
      Line howdyL = new Line("howdy");
      Line farewellL = new Line("farewell");
      Rect blockA = new Rect('a', 3, 4);
      Rect blockB = new Rect('b', 4, 5);
      HComp aBBottom = new HComp(VAlignment.BOTTOM, new AsciiBlock[] {blockA, blockB});
      HComp bACenter = new HComp(VAlignment.CENTER, new AsciiBlock[] {blockB, blockA});
      VComp greetingsCenter = new VComp(HAlignment.CENTER, new AsciiBlock[]  {helloL, goodbyeL, howdyL, farewellL});
      VComp aAndByeLeft = new VComp(HAlignment.LEFT, new AsciiBlock[] {blockA, goodbyeL, farewellL});
      NewBlock hAndH = new NewBlock(aBBottom, bACenter);
      NewBlock vAndV = new NewBlock(greetingsCenter, aAndByeLeft);
      NewBlock hAndV = new NewBlock(aBBottom, greetingsCenter);

      assertEquals(7, hAndH.width(), "aB/bA width");
      assertEquals(8, vAndV.width(), "greetings/aBye width");
      assertEquals(8, hAndV.width(), "aB/greeting width");
      assertEquals(10, hAndH.height(), "aB/bA height");
      assertEquals(8, vAndV.height(), "greetings/aBye height");
      assertEquals(8, hAndV.height(), "aB/greeting height");

      assertEquals("""
                       bbbb
                    bbbbaaa
                    aaabbbb
                    bbbbaaa
                    aaabbbb
                    bbbbaaa
                    aaabbbb
                    bbbbaaa
                    aaabbbb
                    bbbb   
                    """
      , TestUtils.toString(hAndH), "aB/bA");
      assertEquals("""
                     hello  
                    aaa
                    goodbye 
                    aaa
                     howdy  
                    aaa
                    farewell
                    aaa
                    """
      , TestUtils.toString(vAndV), "greetings/aBye");
      assertEquals("""
                     hello  
                       bbbb
                    goodbye 
                    aaabbbb
                     howdy  
                    aaabbbb
                    farewell
                    aaabbbb
                    """
      , TestUtils.toString(hAndV), "aB/greetings");

    } catch (Exception e) {
      e.printStackTrace();
    }
  } // newBlockWithCompTests()

  /**
   * Some tests for NewBlock given surrounded blocks.
   */
  @Test
  public void newBlockWithSurroundedTests() {
    try {

    } catch (Exception e) {
      e.printStackTrace();
    }
  } // newBlockWithSurroundedTests()

  /**
   * Some tests for NewBlock given flipped blocks.
   */
  @Test
  public void newBlockWithFlipTests() {
    try {

    } catch (Exception e) {
      e.printStackTrace();
    }
  } // newBlockWithFlipTests()

  /**
   * Some tests for NewBlock given NewBlock blocks.
   */
  @Test
  public void newBlockWithNewBlockTests() {
    try {

    } catch (Exception e) {
      e.printStackTrace();
    }
  } // newBlockWithNewBlockTests()

} // class TestNewBlock
