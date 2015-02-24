package uk.ac.cam.rfljo2.BlockUp;

/**
 * A representation of a block in the game.
 * @author Robin Otter
 * @author Alastair Toft
 * @author Tom Read-Cutting
 */
public abstract class Block {
	
	//Another change - and yet another
	
	
	protected GameBoard mBoard; // Stores a reference to the board occupied by this block
	
	protected Cell[] mCells;
	
	private Cell pivotPoint; // The point at which the block pivots / rotates
	
	private byte mBlockType;
	
	private boolean isPlaced;
	
	public byte getBlockType() {
		return mBlockType;
	}

	public void setBlockType(byte BlockType) {
		this.mBlockType = BlockType;
	}
	private boolean isHidden; // Holds whether this block is hidden on the GameBoard
	
	/**
	 * Holds the state of rotation
	 * 0 = ground state (Horizontal)
	 * 1 = rotated 90* Clockwise
	 * 2 = rotated 180* (Only for L Block and T Block)
	 * 3 = rotated 270* (Only for L Block and T Block)
	 */
	private byte rotationState;
	
	
	/**
	 * Constructs a new block, given a reference to a GameBoard to place it on
	 * @param b a reference to a GameBoard to place the block on
	 */
	public Block(GameBoard b) {
		isHidden = true; // Block is hidden by default until it is placed
		pivotPoint = new Cell(-10,-10); // pivotpoint set to an arbitrary value
		rotationState = 0;
		isPlaced = false;
		mBoard = b;
		mCells = new Cell[4];
	}
	
	
	/**
	 * Place the block at the specified position on the GameBoard
	 * @param col the column at which to set the PivotPoint
	 * @param row the row at which to set the PivotPoint
	 * @param rotationState the rotation state of the new block when it is placed
	 * @throws CollisionException if the block is placed where there is already a block or boundary
	 * @throws InvalidArgException if the rotationState is not valid for the type of block
	 */
	public boolean place(int col, int row, byte rotationState) throws CollisionException, InvalidArgException {
		if (rotationState < 0 || rotationState > 3) throw new InvalidArgException();
		byte oldRotationState = getRotationState();
		Cell oldPivotPoint = getPivotPoint();
		Cell c = new Cell(col, row);
		if (getPivotPoint() != null) hideBlock();
		setPivotPoint(c);
		setRotationState(rotationState);
		setCells();
		for (Cell d : mCells) {
			if (mBoard.getCell(d.getCol(), d.getRow()) != 0)  {
				setPivotPoint(oldPivotPoint);
				setRotationState(oldRotationState);
				setCells();
				showBlock();
				return false;
			}
		}
		this.showBlock();
		return true;
	}
		
	

	
	public Cell[] getCells() {
		return mCells;
	}




	public abstract void setCells();




	public void hideBlock() {
			for (Cell c: mCells) {
				mBoard.setCell(c.getCol(),c.getRow(),(byte) 0);
			}
			this.setHidden(true);
	}
	
	public void showBlock() {
		for (Cell c: mCells) {
			mBoard.setCell(c.getCol(),c.getRow(),mBlockType);
		}
		this.setHidden(false);
		
	}
	
	public Cell getPivotPoint() {
		return pivotPoint;
	}

	public void setPivotPoint(Cell pivotPoint) {
		this.pivotPoint = pivotPoint;
	}

	public byte getRotationState() {
		return rotationState;
	}

	public void setRotationState(byte rotationState) {
		this.rotationState = rotationState;
	}

	
	/**
	 * Rotates the block clockwise by 90*, increasing its rotation state by 1
	 *
	 */
	public abstract void rotate();
	
	
	/**
	 * Attempts to move the block left one unit. If this raises an exception then the block remains in its original position
	 */
	public void moveLeft() {
		try {
			this.place(getPivotPoint().getCol() - 1, getPivotPoint().getRow(),getRotationState()); // Try to place the block one unit to the left
		} catch (CollisionException | InvalidArgException e) {
			showBlock(); // Show the block in its original position if the move fails
		}
	}
	
	/**
	 * Moves the block right one space, if allowed
	 */
	public void moveRight() {
		try {
			this.place(getPivotPoint().getCol() + 1, getPivotPoint().getRow(), getRotationState()); // Try to place the block one unit to the right
		} catch (CollisionException | InvalidArgException e) {

			showBlock(); // Show the block in its original position if the move fails
		}
	}
	
	/**
	 * Moves the block down one space, if allowed
	 */
	public void moveUp() {
		try {
			this.place(getPivotPoint().getCol(), getPivotPoint().getRow() - 1, getRotationState()); // Try to place the block one unit up
		} catch (CollisionException | InvalidArgException e) {
			showBlock(); // Show the block in its original position if the move fails
			isPlaced = true; // If an up move fails, the block is placed there.
		}
	}
	/**
	 * Tests if the block is hidden
	 * @return true if the block is hidden on the GameBoard
	 */
	public boolean isHidden() {
		return isHidden;
	}
	/**
	 * Set the hidden status of the block
	 * @param isHidden true to set the block as hidden
	 */
	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	/**
	 * @return the isPlaced
	 */
	public boolean isPlaced() {
		return isPlaced;
	}


	
	

}
