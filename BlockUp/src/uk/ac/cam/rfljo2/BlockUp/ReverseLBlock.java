package uk.ac.cam.rfljo2.BlockUp;

/**
 * TODO
 *
 */
public class ReverseLBlock extends Block {

	
	public ReverseLBlock(GameBoard b) {
		super(b);
		setBlockType((byte) 2);
		setCells();
	}
	
	@Override
	public void setCells() {
		Cell[] cells = new Cell[4];
		if (getRotationState() == 0) {
			for (int i = 0; i < 3; i++) {
				Cell c = new Cell(getPivotPoint().getCol() - 1 + i,getPivotPoint().getRow());
				cells[i] = c;
			}
			cells[3] = new Cell(getPivotPoint().getCol() + 1,getPivotPoint().getRow() - 1);
		}
		if (getRotationState() == 1) {
			for (int i = 0; i < 3; i++) {
				Cell c = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow() - 1 + i);
				cells[i] = c;
			}
			cells[3] = new Cell(getPivotPoint().getCol() - 1,getPivotPoint().getRow() - 1);
		}
		if (getRotationState() == 2) {
			for (int i = 0; i < 3; i++) {
				Cell c = new Cell(getPivotPoint().getCol() - 1 + i,getPivotPoint().getRow());
				cells[i] = c;
			}
			cells[3] = new Cell(getPivotPoint().getCol() - 1,getPivotPoint().getRow() + 1);
		}
		if (getRotationState() == 3) {
			for (int i = 0; i < 3; i++) {
				Cell c = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow() - 1 + i);
				cells[i] = c;
			}
			cells[3] = new Cell(getPivotPoint().getCol() + 1,getPivotPoint().getRow() + 1);
		}
		
		mCells = cells;
		
	}
	

	@Override
	public void place(int col, int row, byte rotationState) throws CollisionException, InvalidArgException {
		if (rotationState < 0 || rotationState > 3) throw new InvalidArgException();
		Cell c = new Cell(col, row);
		if (rotationState == 0) {
			if (getPivotPoint() != null) hideBlock();
			for (int i = -1; i < 2; i++) {
				if (mBoard.getCell(col + i, row) != 0) throw new CollisionException();
			}
			if (mBoard.getCell(col + 1, row - 1) != 0) throw new CollisionException();
			setPivotPoint(c);
			this.setRotationState((byte) 0);
			setCells();
			this.showBlock();
		} else if (rotationState == 1) {
			if (getPivotPoint() != null) hideBlock();
			
			for (int i = -1; i < 2; i++) {
				if (mBoard.getCell(col, row + i) != 0) throw new CollisionException();
			}
			if (mBoard.getCell(col - 1, row - 1) != 0) throw new CollisionException();
			setPivotPoint(c);
			this.setRotationState((byte) 1);
			setCells();
			this.showBlock();
		} else if (rotationState == 2) {
			if (getPivotPoint() != null) hideBlock();
			
			for (int i = -1; i < 2; i++) {
				if (mBoard.getCell(col + i, row) != 0) throw new CollisionException();
			}
			if (mBoard.getCell(col - 1, row + 1) != 0) throw new CollisionException();
			setPivotPoint(c);
			this.setRotationState((byte) 2);
			setCells();
			this.showBlock();
		} else if (rotationState == 3) {
			if (getPivotPoint() != null) hideBlock();
			
			for (int i = -1; i < 2; i++) {
				if (mBoard.getCell(col, row + i) != 0) throw new CollisionException();
			}
			if (mBoard.getCell(col + 1, row + 1) != 0) throw new CollisionException();
			setPivotPoint(c);
			this.setRotationState((byte) 3);
			setCells();
			this.showBlock();
		}
		
	}



	@Override
	public void rotate() {
		if (getRotationState() == 3)
			try {
				place(this.getPivotPoint().getCol(),this.getPivotPoint().getRow(),(byte) 0);
			} catch (CollisionException | InvalidArgException e ) {
				showBlock(); // Show the block in its original position if the move fails
			}
		else
			try {
				place(this.getPivotPoint().getCol(),this.getPivotPoint().getRow(),(byte) (getRotationState() + 1));
			} catch (CollisionException | InvalidArgException e) {

				
			}
		
		
	}








	

}
