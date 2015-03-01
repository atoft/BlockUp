package uk.ac.cam.rfljo2.BlockUp;

import uk.ac.cam.rfljo2.BlockUp.blocks.*;

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
	 * Checks if any rows have been filled. If rows are full, moves all non-full rows
	 * into a new Row array, fills the remaining cells with new empty Row objects,
	 * and replaces the original array.
	 * 
	 */
	public void clearFullRows() {
		boolean fullCheck = false;
		for(Row r : mRows){
			if (r.isFull()) { fullCheck = true; break; }
		}
		if(!fullCheck) return;
		
		Row[] newArray = new Row[20];
		int index = 0;
		for(Row r : mRows){
			if(!r.isFull()){
				newArray[index] = r;
				index++;
			}
		}
		for(int j = index; j<20;j++){
			newArray[j]=new Row(10);	
		}
		mRows = newArray;
	}
	
	/**
	 * Empties the contents of every row in the board.
	 */
	public void clearAllRows() {
		for(Row r : mRows){
			r.clear();
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
