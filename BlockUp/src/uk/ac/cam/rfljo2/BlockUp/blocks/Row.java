package uk.ac.cam.rfljo2.BlockUp.blocks;

import java.awt.Color;


/**A representation of a single row in the game grid. Allows for addition of
 * new blocks and testing whether the row is full.
 * @author Robin Otter
 * @author Alastair Toft
 */
public class Row {
	
	private byte[] mArray;
	private Color[] mColours;
	/*
	 * 1 = Turquoise Line
	 * 2 = Blue Reverse L Block
	 * 3 - Orange L Block
	 * 4 - Yellow Square
	 * 5 - Green Squiggly
	 * 6 - Purple T Block
	 * 7 - Red Reverse Squiggly
	 */
	
	
	private int mOccupiedCount; // Counts how many cells in the row are occupied
	
	/**
	 * Sets the specified cell in the row to the given value
	 * @param index the position in the row to set
	 * @param value the value to set the given position to
	 * @param the colour to colour the specified cell
	 */
	public void setCell(int index, byte value, Color colour) {
		if (index > mArray.length - 1 || index < 0) return;
		mArray[index] = value;
		if(value==0) mOccupiedCount--;
		else mOccupiedCount++;
		mColours[index] = colour;
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
	
	/**
	 * Returns the contents of the cell at given index
	 * @param index the index of the cell whose contents is to be returned
	 * @return the contents of the cell at index
	 */
	public Color getColour(int index) {
		if (index > mArray.length - 1 || index < 0) return Color.black;
		return mColours[index];
		
	}
	
	
	
	/**
	 * Constructs an empty row, with the specified number of cells in it
	 * @param cols the number of cells to place in the row
	 */
	public Row(int cols) {
		mArray = new byte[cols];
		mColours = new Color[cols];
		for (int i = 0; i < mColours.length; i++) {
			mColours[i] = Color.black; // Set all cells to black
		}
		mOccupiedCount = 0;
	}
	
	/**
	 * Clears the contents of the row, by setting each cell value to 0
	 */
	public void clear() {
		for (int i = 0; i < mArray.length; i++) {
			mArray[i] = 0;
			mColours[i] = Color.black;
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
