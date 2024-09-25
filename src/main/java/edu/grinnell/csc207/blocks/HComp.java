package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The horizontal composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Tiffany and Lily
 */
public class HComp implements AsciiBlock {
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
  VAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a horizontal composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param leftBlock
   *   The block on the left.
   * @param rightBlock
   *   The block on the right.
   */
  public HComp(VAlignment alignment, AsciiBlock leftBlock,
      AsciiBlock rightBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {leftBlock, rightBlock};
  } // HComp(VAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a horizontal composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public HComp(VAlignment alignment, AsciiBlock[] blocksToCompose) {
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
    String roww = "";
    int maxheight = this.height();
    int offset = -1;

    if (i > maxheight) {
      throw new Exception("ERROR: Out of bound value!");
    } //if
    
    for (int index = 0; index < this.blocks.length; index++) {
      int blockwidth = this.blocks[index].width();
      int blockheight = this.blocks[index].height();
      AsciiBlock block = this.blocks[index];

      if (this.align == VAlignment.TOP){
        offset = 0;
      } else if (this.align == VAlignment.CENTER) {
        offset = (maxheight - blockheight) / 2;
      } else if (this.align == VAlignment.BOTTOM) {
        offset = maxheight - blockheight;
      } else {
        throw new Exception("Error: Invalid alignment!");
      } //if

      if (i < offset || i >= offset + blockheight) {
        roww = roww.concat(" ".repeat(blockwidth));
      } else {
        roww = roww.concat(block.row(index));
      } //if
    } //for
    return roww;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int max = 0;
    for (int i = 0; i < this.blocks.length; i++) {
      if (max < this.blocks[i].height()) {
        max = this.blocks[i].height();
      } //if
    } //for
    return max;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int sum = 0;
    for (int i = 0; i < this.blocks.length; i++) {
      sum += this.blocks[i].width();
    } //for
    return sum;
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
} // class HComp
