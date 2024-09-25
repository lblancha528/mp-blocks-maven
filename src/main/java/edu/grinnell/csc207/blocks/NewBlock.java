package edu.grinnell.csc207.blocks;

/**
 * This function prints two blocks together, alternating their rows.
 */
public class NewBlock implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The first block to build with.
   */
  AsciiBlock blockA;

  /**
   * The second block to build with.
   */
  AsciiBlock blockB;

  /**
   * A value to represent which block is taller.
   */
  AsciiBlock smaller;

  // +-------------+-------------------------------------------------------
  // | Constructor |
  // +-------------+
  public NewBlock(AsciiBlock blockA, AsciiBlock blockB) {
    this.blockA = blockA;
    this.blockB = blockB;
    if (blockA.height() > blockB.height()) {
      this.smaller = blockB;
    } else if (blockB.height() > blockA.height()) {
      this.smaller = blockA;
    } else {
      this.smaller = blockA;
    } // if
  } // NewBlock(AsciiBlock, AsciiBlock)

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
    if (i % 2 == 1) {
      i = (i - 1) / 2;
      return blockA.row(i);
    } else {
      i = (i / 2) - 1;
      return blockB.row(i);
    } // if
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return smaller.height() * 2;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    if (blockA.width() == blockB.width()) {
      return blockA.width();
    } else if (blockA.width() > blockB.width()) {
      return blockA.width();
    } else {
      return blockB.width();
    } // if
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
    return false; //STUB
  };
}
