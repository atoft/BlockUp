package uk.ac.cam.rfljo2.BlockUp;

/**
 * Line Block
 * @author Robin Otter
 *
 */
public class Square extends Block {

	
	public Square(GameBoard b) {
		super(b);
		setBlockType((byte) 4);
		setCells();
	}

	@Override
	public void setCells() {
		Cell[] cells = new Cell[4];
			for (int i = 0; i < 2; i++) {
				Cell c = new Cell(getPivotPoint().getCol() + i,getPivotPoint().getRow());
				Cell d = new Cell(getPivotPoint().getCol() + i,getPivotPoint().getRow() - 1);
				cells[i] = c;
				cells[i+2] = d;
			}

		mCells = cells;
		
	}	
	
	
	

	@Override
	public void place(int col, int row, byte rotationState) throws CollisionException, InvalidArgException {
		if (rotationState < 0 || rotationState > 0) throw new InvalidArgException();
		Cell c = new Cell(col, row);
		if (getPivotPoint() != null) hideBlock();
		for (int i = 0; i < 2; i++) {
			if (mBoard.getCell(col + i, row) != 0) throw new CollisionException();
			if (mBoard.getCell(col + i, row - 1) != 0) throw new CollisionException();
		}
			
			
		setPivotPoint(c);
		this.setRotationState((byte) 0);
		setCells();
		this.showBlock();
		
	}



	@Override
	public void rotate() {
		// Square cannot rotate.
	}




	

}
