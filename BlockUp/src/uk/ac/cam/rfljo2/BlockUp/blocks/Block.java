package uk.ac.cam.rfljo2.BlockUp.blocks;
import uk.ac.cam.rfljo2.BlockUp.*;

import java.util.Queue;

/**
 * A representation of a block in the game.
 * 
 * @author Robin Otter
 * @author Alastair Toft
 * @author Tom Read-Cutting
 */
public abstract class Block {

	private GameBoard mBoard; // Stores a reference to the board occupied by
								// this block
	private Cell[] mCells; // Cells belonging to that block

	private Queue<Byte> mRotationQueue; // The Q holding the next rotation state

	private Cell mPivotPoint; // The point at which the block pivots / rotates

	private byte mBlockType;

	private boolean isFinallyPlaced;

	private byte rotationState;

	
	
	/**
	 * Constructs a new block, given a reference to a GameBoard to place it on
	 * @param b a reference to a GameBoard to place the block on
	 */
	protected Block(GameBoard b, byte type) {
		mBlockType = type;
		rotationState = 0;
		isFinallyPlaced = false;
		mBoard = b;
		mCells = new Cell[4];
		for (int i = 0; i < mCells.length; i++) {
			mCells[i] = new Cell(-10, -10,getBlockType());
		}
		setPivotPoint(new Cell(-10, -10,getBlockType())); // PivotPoint set to an arbitrary
		// value
		regenerateCells();
	}

	/**
	 * Returns the array containing the Cells belonging to this block
	 * 
	 * @return an array of the Cells belonging to this block
	 */
	public Cell[] getCells() {
		return mCells;
	}

	/**
	 * Sets the cells belonging to this block to a copy of the given Array
	 * @param cells an array containing the new cells that will belong to this block
	 */
	public void setCells(Cell[] cells) {
		mCells = cells.clone();
	}
	
	/**
	 * Set a Queue containing the next rotationStates of the block
	 * @param rotationStates a Queue of the next rotationStates of the block
	 */
	public void setRotationQueue(Queue<Byte> rotationStates) {
		this.mRotationQueue = rotationStates;
	}
	
	/**
	 * Returns the new Cell at which this block currently pivots / rotates
	 * @return the current pivotPoint of the cell
	 */
	public Cell getPivotPoint() {
		return mPivotPoint;
	}

	/**
	 * Sets the pivot point of this to the specified Cell
	 * @param pivotPoint the Cell to set as the new pivotPoint of this
	 */
	public void setPivotPoint(Cell pivotPoint) {
		this.mPivotPoint = pivotPoint;
	}
	
	/**
	 * Returns the type of block as a byte
	 * @return the type of the block
	 */
	public byte getBlockType() {
		return mBlockType;
	}
	
	/**
	 * Sets the BlockType of this to the specified value
	 * @param blockType the value of the blockType to be set
	 */
	public void setBlockType(byte blockType) {
		this.mBlockType = blockType;
	}
	
	/**
	 * Tests if the block has been finally placed
	 * @return true if this block has been finally placed
	 */
	public boolean isFinallyPlaced() {
		return isFinallyPlaced;
	}

	
	/**
	 * Regenerates the Cell Array associated with this block, based upon the current rotationState and pivotPoint
	 */
	public abstract void regenerateCells();

	/**
	 * Hides the block from the GameBoard
	 */
	public void hideBlock() {
		for (Cell c : mCells) {
			mBoard.setCell(c.getCol(), c.getRow(), (byte) 0);
		}
	}

	/**
	 * Shows the block on the GameBoard, based upon its current rotationState and pivotPoint
	 */
	public void showBlock() {
		for (Cell c : mCells) {
			mBoard.setCell(c.getCol(), c.getRow(), mBlockType);
		}

	}

	/**
	 * Returns the current state of rotation of this 
	 * 0 = ground state (Horizontal) 
	 * 1 = rotated 90* Clockwise 
	 * 2 = rotated 180* (Only for L Block and T Block) 
	 * 3 = rotated 270* (Only for L Block and T Block)
	 */
	public byte getRotationState() {
		return rotationState;
	}

	/**
	 * Sets the current state of rotation to the specified value
	 * @param rotationState the new value of the rotationState of this block
	 */
	public void setRotationState(byte rotationState) {
		this.rotationState = rotationState;
	}

	
	/**
	 * Place the block at the specified position on the GameBoard
	 * @param col the column at which to set the PivotPoint
	 * @param row the row at which to set the PivotPoint
	 * @param rotationState the rotation state of the new block when it is placed
	 * @throws InvalidArgException if the rotationState is not valid for the type of block
	 * @return true if the block can be successfully placed without collision
	 */
	public boolean place(int col, int row, byte rotationState)
			throws InvalidArgException {
		if (rotationState < 0 || rotationState > 3)
			throw new InvalidArgException();
		byte oldRotationState = getRotationState();
		Cell oldPivotPoint = getPivotPoint();
		Cell c = new Cell(col, row, mBlockType);
		if (getPivotPoint() != null)
			hideBlock();
		setPivotPoint(c);
		setRotationState(rotationState);
		regenerateCells();
		for (Cell d : mCells) {
			if (mBoard.getCell(d.getCol(), d.getRow()) != 0) {
				setPivotPoint(oldPivotPoint);
				setRotationState(oldRotationState);
				regenerateCells();
				showBlock();
				return false;
			}
		}
		this.showBlock();
		return true;
	}

	/**
	 * Rotates the block clockwise by 90*, increasing its rotation state by 1
	 *
	 */
	public void rotateLeft() {
		byte b = mRotationQueue.poll();
		try {
			// try to place the block rotated 90* clockwise
			this.place(getPivotPoint().getCol(), getPivotPoint().getRow(), b); 
			mRotationQueue.add(b);
		} catch (InvalidArgException e) {
			showBlock(); // Show the block in its original position if the move
							// fails
			mRotationQueue.add(b);
		}
	}

	/**
	 * Attempts to move the block left one unit. If this fails
	 * then the block remains in its original position
	 */
	public void moveLeft() {
		try {
			this.place(getPivotPoint().getCol() - 1, getPivotPoint().getRow(),
					getRotationState()); // Try to place the block one unit to
											// the left
		} catch (InvalidArgException e) {
			showBlock(); // Show the block in its original position if the move
							// fails
		}
	}

	/**
	 * Attempts to move the block right one unit. If this fails
	 * then the block remains in its original position
	 */
	public void moveRight() {
		try {
			this.place(getPivotPoint().getCol() + 1, getPivotPoint().getRow(),
					getRotationState()); // Try to place the block one unit to
											// the right
		} catch (InvalidArgException e) {

			showBlock(); // Show the block in its original position if the move
							// fails
		}
	}

	/**
	 * Attempts to move the block up one unit. If this fails
	 * then the block remains in its original position and it is
	 * set to be finallyPlaced.
	 */
	public void moveUp() {
		try {
			// Try to place the block one unit up
			boolean b = this.place(getPivotPoint().getCol(), getPivotPoint()
					.getRow() - 1, getRotationState()); 
			if (b == false)
				isFinallyPlaced = true; // If an up move fails, the block is
										// finallyPlaced
		} catch (InvalidArgException e) {
			showBlock(); // Show the block in its original position if the move
							// fails

		}
	}

}
