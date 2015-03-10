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
	public int getY() {
		return mRow;
	}
	
	/**
	 * Gets the current column stored in the cell
	 * @return the integer value of the column of the cell
	 */
	public int getX() {
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

	public static void applyRotation(Piece[] pieces, int rotationState) {
		if (rotationState == 0) return;
		switch (rotationState) {
			case 1:
				for(int i = 0; i < pieces.length; i++) {
					pieces[i] = new Piece(-pieces[i].getY(), pieces[i].getX(), pieces[i].getType());
				}
				break;
			case 2:
				for(int i = 0; i < pieces.length; i++) {
					pieces[i] = new Piece(-pieces[i].getX(), -pieces[i].getY(), pieces[i].getType());
				}
				break;
			case 3:
				for(int i = 0; i < pieces.length; i++) {
					pieces[i] = new Piece(pieces[i].getY(), -pieces[i].getX(), pieces[i].getType());
				}
				break;	
		}
		
	}
}
