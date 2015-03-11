package uk.ac.cam.rfljo2.BlockUp;


/**A representation of a single row in the game grid. Allows for addition of
 * new blocks and testing whether the row is full.
 * @author Robin Otter
 * @author Alastair Toft
 */
public class Row {
	
	private byte[] mArray;
	
	
	private int mOccupiedCount; // Counts how many cells in the row are occupied
	
	/**
	 * Sets the specified cell in the row to the given value
	 * @param index the position in the row to set
	 * @param value the value to set the given position to
	 * @param the colour to colour the specified cell
	 */
	public void setCell(int index, byte type) {
		if (index > mArray.length - 1 || index < 0) return;
		mArray[index] = type;
		if(type==0) mOccupiedCount--;
		else mOccupiedCount++;
	}
	
	/**
	 * Returns the contents of the cell at given index
	 * @param index the index of the cell whose contents is to be returned
	 * @return the contents of the cell at index
	 */
	public byte getCell(int index) {
		if (index > mArray.length - 1 || index < 0) return -1;
		return mArray[index];
		
	}
	
	public byte[] getCells() {
		return mArray;
	}
	
	
	
	/**
	 * Constructs an empty row, with the specified number of cells in it
	 * @param cols the number of cells to place in the row
	 */
	public Row(int cols) {
		mArray = new byte[cols];
		mOccupiedCount = 0;
	}
	
	/**
	 * Clears the contents of the row, by setting each cell value to 0
	 */
	public void clear() {
		for (int i = 0; i < mArray.length; i++) {
			mArray[i] = 0;
		}
		mOccupiedCount=0;
		
	}
	
	
	/**
	 * @return true if the row is completely full of blocks
	 */
	public boolean isFull() {
		return (mOccupiedCount > mArray.length - 1);
	}
	
	/**
	 * Returns the length of the row
	 * @return the length of the row
	 */
	public int length() {
		return mArray.length;
	}
	

}
