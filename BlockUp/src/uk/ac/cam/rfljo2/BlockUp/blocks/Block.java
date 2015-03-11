package uk.ac.cam.rfljo2.BlockUp.blocks;
import uk.ac.cam.rfljo2.BlockUp.*;

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
	 * Returns the current state of rotation of this 
	 * 0 = ground state (Horizontal) 
	 * 1 = rotated 90* Clockwise 
	 * 2 = rotated 180* (Only for L Block and T Block) 
	 * 3 = rotated 270* (Only for L Block and T Block)
	 */
	public int getRotationState() {
		return mRotationState;
	}
	public void setRotationState(int state){
		mRotationState=state;
	}

	/**
	 * Rotates the block clockwise by 90*,
	 *
	 */
	public void rotateClockwise() {
		setRotationState(mRotationState + 1);
	}
	
	/**
	 * Rotates the block clockwise by -90*,
	 *
	 */
	public void rotateAntiClockwise() {
		setRotationState(mRotationState - 1);
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
