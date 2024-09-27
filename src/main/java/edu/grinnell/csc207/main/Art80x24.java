package edu.grinnell.csc207.main;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.Surrounded;
import edu.grinnell.csc207.blocks.Grid;
import java.io.PrintWriter;

/**
 * Create and print an amazing 80x24 ASCII artwork.
 *
 * @author Your Name Here
 * @author Your Name Here
 */
public class Art80x24 {
  /**
   * Create the artwork.
   *
   * @param args
   *   Command-line arguments (currently ignored).
   *
   * @exception Exception
   *   If something goes wrong with one of the underlying classes.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    AsciiBlock art = new Rect('~', 80, 2);
    AsciiBlock.print(pen, art);
    Line line = new Line("(♡૮ ˶ᵔ ᵕ ᵔ˶ ა♡)-");
    AsciiBlock surroundedLine = new Surrounded(line, '+');
    AsciiBlock surroundedLine2 = new Surrounded(surroundedLine, '|');
    Grid artp = new Grid(surroundedLine2, 4, 4);
    AsciiBlock.print(pen, artp);
    AsciiBlock.print(pen, art);
    pen.close();
  } // main(String[])
} // class Art80x24
