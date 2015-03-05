package uk.ac.cam.rfljo2.BlockUp;

import java.awt.Color;

/**
 * Holds a pair containing a column and a row value
 */
public class Piece {
	
	private final int mCol;
	private final int mRow;
	private byte mType;
	
	
	public Piece(int column, int row, byte type) {
		mCol = column;
		mRow = row;
		mType = type;
	}
	
	/**
	 * Gets the current row stored in the cell
	 * @return the integer value of the row of the cell
	 */
	public int getRow() {
		return mRow;
	}
	
	/**
	 * Gets the current column stored in the cell
	 * @return the integer value of the column of the cell
	 */
	public int getCol() {
		return mCol;
	}
	
	/**
	 * @return the Type of the cell
	 */
	public byte getType() {
		return mType;
	}
	
	public void setType(byte type) {
		mType = type;
	}

	/**
	 * Equality test for cells
	 * @param c the cell to compare to
	 * @return true if the row and column of c are equal to the row and column of this
	 */
	public boolean equals(Piece c) {
		return (c.mCol == this.mCol && c.mRow == this.mRow);
	}
}
