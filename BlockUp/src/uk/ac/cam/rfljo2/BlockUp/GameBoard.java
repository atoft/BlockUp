package uk.ac.cam.rfljo2.BlockUp;

import java.util.LinkedList;

import uk.ac.cam.rfljo2.BlockUp.blocks.*;

/**
 * A representation of a BlockUp Game Board. Will keep track of which cells in the game are occupied.
 * @author Robin Otter
 * @author Alastair Toft
 */
public class GameBoard {
	
	private Row[] mRows; // Row 0 is at the top
	private Block mActiveBlock;
	
	/**
	 * Constructs a blank GameBoard with a specified number of rows
	 * @param width the number of cells to place in each row
	 * @param height the number of rows to add to the GameBoard
	 */
	public GameBoard(int width, int height) {
		mRows = new Row[height]; 
		for (int i = 0; i < mRows.length; i++) {
			mRows[i] = new Row(width);
		}
		
	}
	
	/**
	 * Checks if any rows have been filled. If rows are full, moves all non-full rows
	 * into a new Row array, fills the remaining cells with new empty Row objects,
	 * and replaces the original array.
	 * 
	 */
	public LinkedList<Integer> clearFullRows() {
		boolean fullCheck = false;
		for(Row r : mRows){
			if (r.isFull()) { fullCheck = true; break; }
		}
		if(!fullCheck) return null;
		
		Row[] newArray = new Row[GameConstants.BOARD_HEIGHT];
		LinkedList<Integer> removed = new LinkedList<Integer>();
		int index = 0;
		for(Row r : mRows){
			if(!r.isFull()){
				newArray[index] = r;
				index++;
			}
			else removed.add(index);
		}
		for(int j = index; j<GameConstants.BOARD_HEIGHT;j++){
			newArray[j]=new Row(GameConstants.BOARD_WIDTH);	
		}
		mRows = newArray;
		
		return removed;
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
	 * @param x the column in which to place the value
	 * @param y the row in which to place the value
	 * @param value the value to store in the GameBoard
	 */
	public void setCell(int x, int y, byte type) {
		if (y > mRows.length - 1 || y < 0) return;
		if (x > mRows[0].length() - 1 || x < 0) return;
		mRows[y].setCell(x, type);
	}
	
	/**
	 * Returns the cell value at the specified row and column
	 * @param x the column of the cell in question
	 * @param y the row of the cell in question
	 * @return the byte value contained in the specified cell
	 */
	public byte getCell(int x, int y) {
		if (y > mRows.length - 1 || y < 0) return BlockType.OUT_OF_BOUNDS; 
		if (x > mRows[0].length() - 1 || x < 0) return BlockType.OUT_OF_BOUNDS;
		return mRows[y].getCell(x);
	}
	
	public void setActiveBlock(Block b) {
		mActiveBlock = b;
	}

	public Block getActiveBlock() {
		return mActiveBlock;
	}
	
	/**
	 * Checks whether the current block, when moved to coordinates (x,y) collides with anything else
	 * in the board.
	 * 
	 * @param x
	 * @param y
	 * @return True if a collision occurs.
	 */
	public boolean collisionTest(int x, int y){
		

			
		for(Piece p : mActiveBlock.getPieces()){
			int testX=p.getX()+x;
			int testY=p.getY()+y;
			
			if(  (this.getCell(testX,testY)==BlockType.OUT_OF_BOUNDS || this.getCell(testX, testY)!=BlockType.EMPTY)){
				
				System.out.println("Collision at: "+testX+","+testY);
				return true;
			}
		}
		
		return false;
		
	}
	
	public void placeBlock(){
		for(Piece p : mActiveBlock.getPieces()){
			this.setCell(p.getX()+mActiveBlock.getX(), p.getY()+mActiveBlock.getY(), p.getType());
		}
	}
}
