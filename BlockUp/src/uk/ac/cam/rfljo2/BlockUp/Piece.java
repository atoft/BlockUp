package uk.ac.cam.rfljo2.BlockUp;

import java.awt.Color;

/**
 * Holds a pair containing a column and a row value
 */
public class Piece {
	
	private final int mX;
	private final int mY;
	private byte mType;
	
	
	public Piece(int x, int y, byte type) {
		mX = x;
		mY = y;
		mType = type;
	}
	
	/**
	 * Gets the current row stored in the cell
	 * @return the integer value of the row of the cell
	 */
	public int getY() {
		return mY;
	}
	
	/**
	 * Gets the current column stored in the cell
	 * @return the integer value of the column of the cell
	 */
	public int getX() {
		return mX;
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
		return (c.mX == this.mX && c.mY == this.mY);
	}
	
	/**
	 * Rotates the block 90 degrees clockwise
	 */
	public Piece rotate90() {
		return new Piece(-mY, mX, mType);
	}
	
	/**
	 * Rotates the block 180 degrees clockwise
	 */
	public Piece rotate180() {
		return new Piece(-mX, -mY, mType);
	}
	
	/**
	 * Rotates the block 270 degrees clockwise
	 */
	public Piece rotate270() {
		return new Piece(mY, -mX, mType);
	}

}
