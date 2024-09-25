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

/**
 * Tests of the new block.
 */
public class TestNewBlock {
  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * A placeholder.
   */
  @Test
  public void placeholder() {
    try {
      Rect blockA = new Rect('a', 3, 4);
      Rect blockB = new Rect('b', 4, 5);
      Rect blockC = new Rect('c', 2, 2);
      Rect blockD = new Rect('d', 3, 2);
      NewBlock aAndB = new NewBlock(blockA, blockB);
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
      , TestUtils.toString(aAndB), "a and b");
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
   
  } // placeholder()

} // class TestNewBlock
