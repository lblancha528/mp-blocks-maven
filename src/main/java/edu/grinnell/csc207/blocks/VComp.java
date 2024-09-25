package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The vertical composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 * @author Your Name Here
 */
public class VComp implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The blocks.
   */
  AsciiBlock[] blocks;

  /**
   * How the blocks are aligned.
   */
  HAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a vertical composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param topBlock
   *   The block on the top.
   * @param bottomBlock
   *   The block on the bottom.
   */
  public VComp(HAlignment alignment, AsciiBlock topBlock,
      AsciiBlock bottomBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {topBlock, bottomBlock};
  } // VComp(HAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a vertical composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.LEFT
   */

  public VComp(HAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // HComp(Alignment, AsciiBLOCK[])

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
   * @exception Exception
   *   if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    int j = 0;
    for (; j < blocks.length; j++) {
      if (i > blocks[j].height()) {
        i -= blocks[j].height();
      } // if
    } // for
    // j is now a valid row in the right block

    int gap = findGap(blocks[j]);
    String Lgap = "";
    String Rgap = "";
    if ((gap % 2) == 1) {
      // if gap is odd, put larger chunk in Rgap
      Lgap = " ".repeat(gap);
      Rgap = " ".repeat(gap + 1);
    } else {
      Lgap = " ".repeat(gap);
      Rgap = " ".repeat(gap);
    } // else

    String roww = "";
    if (align == HAlignment.LEFT) {
      roww = blocks[j].row(i) + Lgap + Rgap;
    } else if (align == HAlignment.CENTER) {
      roww = Lgap + blocks[i].row(i) + Rgap;
    } else if (align == HAlignment.RIGHT) {
      roww = Lgap + Rgap + blocks[i].row(i);
    } else {
      System.err.println("ERROR: Invalid alignment.");
    }
    return roww;
    
  } // row(int)

  /**
   * Get the top and bottom gap size for a block.
   * Prioritizes less space on top.
   * 
   * @param block
   *   the block to be analyzed
   * @return gap
   *   the number of spaces needed either side of the block
   */
  public int findGap(AsciiBlock block) {
    int gap = this.width() - block.width();
    gap /= 2;
    return gap;
  } // findGap(AsciiBlock)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int tall = 0;
    for (int i = 0; i < blocks.length; i++) {
      if (blocks[i].height() > tall) {
        tall = blocks[i].height();
      } // if
    } // for
    return tall;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int wide = 0;
    for (int i = 0; i < blocks.length; i++) {
      wide += blocks[i].width();
    } // for
    return wide;
  } // width()

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
    return false;       // STUB
  } // eqv(AsciiBlock)
} // class VComp
