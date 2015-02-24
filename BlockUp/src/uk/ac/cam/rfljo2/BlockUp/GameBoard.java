package uk.ac.cam.rfljo2.BlockUp;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A representation of a BlockUp Game Board. Will keep track of which cells in the game are occupied.
 * @author Robin Otter
 * @author Alastair Toft
 */
public class GameBoard {
	
	private Row[] mRows; // Row 0 is at the top
	
	private Block activeBlock;	// reference to the block on the board that is active and can be moved
	private Block nextBlock;	// the block which will be added once the current block lands
	
	/**
	 * Constructs a blank GameBoard with a specified number of rows
	 * @param rows the number of rows to add to the GameBoard
	 * @param cols the number of cells to place in each row
	 */
	public GameBoard(int rows, int cols) {
		mRows = new Row[rows]; 
		for (int i = 0; i < mRows.length; i++) {
			mRows[i] = new Row(cols);
		}
		
	}
	
	/**
	 * Starting from the bottom of the board, this method creates a list of all the non-full rows, and then copies these into the board, 
	 * effectively clearing the board of any full rows
	 * 
	 */
	public void clearRows() {

		Queue<Row> q = new LinkedList<Row>();
		// Store all rows that are not full in a new Queue
		for (Row r : mRows) {
			if (r.isFull() == false) {
				q.add(r);
			}
		}
		// Clear every row in the GameBoard
		for (int i = 0; i < mRows.length; i++) {
			mRows[i].clear();
		}
		int j = 0;
		
		// Starting from the bottom, add all the non empty rows back into the gameboard
		while (q.isEmpty() == false) {
			mRows[j] = q.poll();
		}
	}
	
	
	/**
	 * Sets a cell value in the GameBoard, specified by a row and column
	 * @param col the column in which to place the value
	 * @param row the row in which to place the value
	 * @param value the value to store in the GameBoard
	 */
	public void setCell(int col, int row, byte value) {
		if (row > mRows.length - 1 || row < 0) return;
		if (col > mRows[0].length() - 1 || col < 0) return;
		mRows[row].setCell(col, value);
	}
	
	/**
	 * Returns the cell value at the specified row and column
	 * @param col the column of the cell in question
	 * @param row the row of the cell in question
	 * @return the byte value contained in the specified cell
	 */
	public byte getCell(int col, int row) {
		if (row > mRows.length - 1 || row < 0) return -1; 
		if (col > mRows[0].length() - 1 || col < 0) return -1;
		return mRows[row].getCell(col);
	}

	
	public Block getActiveBlock() {
		return activeBlock;
	}

	public void setActiveBlock(Block activeBlock) {
		this.activeBlock = activeBlock;
	}

	public Block getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(Block nextBlock) {
		this.nextBlock = nextBlock;
	}
}
