package edu.grinnell.csc207.blocks;

import java.io.PrintWriter;

/**
 * A text block surrounded by a single letter.
 *
 * @author Lily Blanchard
 * @author Tiffany Yan
 */
public class Surrounded implements AsciiBlock {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The stuff in the box.
   * Copied from Lab 6 with Sarah Deschamps
   */
  AsciiBlock contents;

  /**
   * The character we put around the box.
   */
  String boxChar;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  // line, char
  // rect, char

  /**
   * Construct a block by surrounding the provided AsciiBlock with the provided character.
   * @param block
   * @param char
   */
  public Surrounded(AsciiBlock block, char ch) {
    this.contents = block;
    this.boxChar = ch + "";
  }

  // +----------------+----------------------------------------------
  // | Static methods |
  // +----------------+

  /**
   * Print out a block.
   *
   * @param pen
   *   The PrintWriter used to print the block.
   *
   * @param block
   *   The block to print.
   */
  public static void print(PrintWriter pen, AsciiBlock block) {
    for (int i = 0; i < block.height(); i++) {
      try {
        pen.println(block.row(i));
      } catch (Exception e) {
        pen.printf("*** ERROR: Missing row %d ***\n", i);
      } // try/catch
    } // for
  } // print(PrintWriter, AsciiBlock)

  /**
   * Determine if two blocks are equal in that they occupy the same
   * memory location.
   *
   * @param block1
   *   One of the two blocks.
   * @param block2
   *   The other block.
   *
   * @return true if they are in the same memory location and false otherwise.
   */
  public static boolean eq(AsciiBlock block1, AsciiBlock block2) {
    return block1 == block2;
  } // eq(AsciiBlock, AsciiBlock)

  /**
   * Determine if two blocks are equal in that they are structurally
   * equivalent. That is, two blocks are equal in this sense if they
   * were created with the same set of nested constructor calls.
   *
   * @param block1
   *   One of the two blocks.
   * @param block2
   *   The other block.
   *
   * @return true if they are structurally equivalent and false otherwise.
   */
  public static boolean eqv(AsciiBlock block1, AsciiBlock block2) {
    return ((block1 instanceof Surrounded) && (block1.eqv((Surrounded) block1)) && 
    (block2 instanceof Surrounded) && (block2.eqv((Surrounded) block2)));
  } // eqv(AsciiBlock, AsciiBlock)



  /**
   * Determine if another grid is structurally equivalent to this grid.
   *
   * @param block1
   *   One of the two blocks
   * @param block2
   *   The other block.
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(Surrounded block1, Surrounded block2) {
    return (block1.contents.eqv(block2.contents) && block1.boxChar.equals(block2.boxChar));
  } // eqv(Surrounded, surrounded)

  /**
   * Determine if two blocks are equal in that they have the same,
   * width, height, and rows.
   *
   * @param block1
   *   One of the two blocks.
   * @param block2
   *   The other block.
   *
   * @return true if they are in the same memory location and false otherwise.
   */
  public static boolean equal(AsciiBlock block1, AsciiBlock block2) {
    if (block1.width() != block2.width()) {
      return false;
    } // if
    if (block1.height() != block2.height()) {
      return false;
    } // if
    for (int i = 0; i < block1.height(); i++) {
      try {
        if (!block1.row(i).equals(block2.row(i))) {
          return false;
        } // if
      } catch (Exception e) {
        return false;
      } // try/catch
    } // for
    return true;
  } // equal(AsciiBlock, AsciiBlock)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @pre
   *   0 <= i < this.height()
   *
   * @exception Exception
   *   if the row number is invalid.
   */
  public String row(int i) throws Exception {
    int h = this.contents.height();
    if (i == 0 || i == h + 1) {
      // The top of the box
      return this.boxChar.repeat((this.contents.width()) + 2);
    } else if ((i > 0) && (i <= h)) {
      // Stuff within the box
      return this.boxChar + this.contents.row(i - 1) + this.boxChar;
    } else {
      throw new Exception("Invalid row " + i);
    } // if/else
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return this.contents.height() + 2;
  };

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return this.contents.width() + 2;
  };

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    return false; //STUB
  } // eqv(AsciiBlock)
} // class Surrounded