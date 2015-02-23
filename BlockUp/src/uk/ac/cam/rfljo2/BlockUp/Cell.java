package uk.ac.cam.rfljo2.BlockUp;
/**
 * Holds a pair containing a column and a row value
 */
public class Cell {
	
	private final int mCol;
	private final int mRow;
	
	
	/**
	 * Constructs a new cell with given row and column values
	 * @param column	the value of the column to be stored in the cell
	 * @param row		the value of the row to be stored in the cell
	 */
	public Cell(int column, int row) {
		mCol = column;
		mRow = row;
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
	 * Equality test for cells
	 * @param c the cell to compare to
	 * @return true if the row and column of c are equal to the row and column of this
	 */
	public boolean equals(Cell c) {
		return (c.mCol == this.mCol && c.mRow == this.mRow);
	}



}
