package uk.ac.cam.rfljo2.BlockUp.blocks;
import uk.ac.cam.rfljo2.BlockUp.*;

import java.awt.Color;
import java.util.Queue;
import java.util.Random;

/**
 * A representation of a block in the game.
 * 
 * @author Robin Otter
 * @author Alastair Toft
 * @author Tom Read-Cutting
 */
public class Block {

	private Piece[] mPieces; // Cells belonging to that block
	private int mX;
	
	private int mY;

	private int mRotationState;

	
	
	/**
	 * Constructs a new vanilla block. Setting all cells to a certain type.
	 * @param b a reference to a GameBoard to place the block on
	 */
	protected Block() {
		mRotationState = 0;
	}

	

	/**
	 * Returns the array containing the Cells belonging to this block
	 * 
	 * @return an array of the Cells belonging to this block
	 */
	public Piece[] getPieces() {
		return mPieces;
	}

	/**
	 * Hides the block from the GameBoard
	 */ /*
	public void hideBlock(GameBoard board) {
		for (Piece c : mCells) {
			board.setCell(c.getCol(), c.getRow(), (byte) 0);
		}
	}

	/**
	 * Shows the block on the GameBoard, based upon its current rotationState and pivotPoint
	 */ /*
	public void showBlock(GameBoard board) {
		for (Piece c : mCells) {
			board.setCell(c.getCol(), c.getRow(),c.getType());
		}

	} */

	/**
	 * Returns the current state of rotation of this 
	 * 0 = ground state (Horizontal) 
	 * 1 = rotated 90* Clockwise 
	 * 2 = rotated 180* (Only for L Block and T Block) 
	 * 3 = rotated 270* (Only for L Block and T Block)
	 */
	public int getRotationState() {
		return mRotationState;
	}

	/**
	 * Sets the current state of rotation to the specified value
	 * @param rotationState the new value of the rotationState of this block
	 */
	public void setRotationState(int rotationState) {
		mRotationState = rotationState;
	}

	
	/**
	 * Place the block at the specified position on the GameBoard
	 * @param col the column at which to set the PivotPoint
	 * @param row the row at which to set the PivotPoint
	 * @param rotationState the rotation state of the new block when it is placed
	 * @throws InvalidArgException if the rotationState is not valid for the type of block
	 * @return true if the block can be successfully placed without collision
	 *//*
	public boolean place(int col, int row, byte rotationState, GameBoard board)
			throws InvalidArgException {
		if (rotationState < 0 || rotationState > 3)
			throw new InvalidArgException();
		byte oldRotationState = getRotationState();
		Piece oldPivotPoint = getPivotPoint();
		Piece c = new Piece(col, row,(byte) 0); //TODO: look at this
		if (getPivotPoint() != null)
			hideBlock(board);
		setPivotPoint(c);
		setRotationState(rotationState);
		regenerateCells();
		for (Piece d : mCells) {
			if (board.getCell(d.getCol(), d.getRow()) != 0) {
				setPivotPoint(oldPivotPoint);
				setRotationState(oldRotationState);
				regenerateCells();
				showBlock(board);
				return false;
			}
		}
		this.showBlock(board);
		return true;
	} */

	/**
	 * Rotates the block clockwise by 90*,
	 *
	 */
	public void rotateClockwise() {
		mRotationState = (mRotationState + 1) % 4;
	}
	
	/**
	 * Rotates the block clockwise by -90*,
	 *
	 */
	public void rotateAntiClockwise() {
		mRotationState = (mRotationState - 1) % 4;
	}
	
	public void setX(int x) {
		mX = x;
	}
	
	public int getX() {
		return mX;
	}
	
	public void setY(int y) {
		mY = y;
	}
	
	public int getY() {
		return mY;
	}
	
	public void setPieces(Piece[] pieces) {
		mPieces = pieces;
	}
}
