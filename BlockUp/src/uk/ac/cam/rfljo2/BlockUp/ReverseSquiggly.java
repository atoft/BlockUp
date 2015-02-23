package uk.ac.cam.rfljo2.BlockUp;

/**
 * Line Block
 * @author Robin Otter
 *
 */
public class ReverseSquiggly extends Block {
	
	
	
	
	
	public ReverseSquiggly(GameBoard b) {
		super(b);
		setBlockType((byte) 6);
		setCells();
	}
	
	
	@Override
	public void setCells() {
		Cell[] cells = new Cell[4];
		if (getRotationState() == 0) {
			for (int i = 0; i < 2; i++) {
				Cell c = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow() - i);
				cells[i] = c;
			}
			cells[2] = new Cell(getPivotPoint().getCol() - 1,getPivotPoint().getRow());
			cells[3] = new Cell(getPivotPoint().getCol() + 1,getPivotPoint().getRow() - 1);
		}
		if (getRotationState() == 1) {
			for (int i = 0; i < 2; i++) {
				Cell c = new Cell(getPivotPoint().getCol() - i,getPivotPoint().getRow());
				cells[i] = c;
			}
			cells[2] = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow() + 1);
			cells[3] = new Cell(getPivotPoint().getCol() - 1,getPivotPoint().getRow() - 1);
		}
		
		mCells = cells;
		
	}
	@Override
	public void place(int col, int row, byte rotationState) throws CollisionException, InvalidArgException {
		if (rotationState < 0 || rotationState > 1) throw new InvalidArgException();
		Cell c = new Cell(col, row);
		if (rotationState == 0) {
			if (getPivotPoint() != null) hideBlock();
			for (int i = 0; i < 2; i++) {
				if (mBoard.getCell(col, row - i) != 0) throw new CollisionException();
			}
			if (mBoard.getCell(col - 1, row) != 0) throw new CollisionException();
			if (mBoard.getCell(col + 1, row) != 0) throw new CollisionException();
			
			
			setPivotPoint(c);
			this.setRotationState((byte) 0);
			setCells();
			this.showBlock();
		} else {
			if (getPivotPoint() != null) hideBlock();
			
			for (int i = 0; i < 2; i++) {
				if (mBoard.getCell(col - i, row) != 0) throw new CollisionException();
			}
			if (mBoard.getCell(col, row + 1) != 0) throw new CollisionException();
			if (mBoard.getCell(col - 1, row - 1) != 0) throw new CollisionException();
			
			setPivotPoint(c);
			this.setRotationState((byte) 1);
			setCells();
			this.showBlock();
		}
		
	}



	@Override
	public void rotate() {
		if (getRotationState() == 0)
			try {
				place(this.getPivotPoint().getCol(),this.getPivotPoint().getRow(),(byte) 1);
			} catch (CollisionException e) {
				showBlock();
			} catch (InvalidArgException e) {
				showBlock();
			}
		else
			try {
				place(this.getPivotPoint().getCol(),this.getPivotPoint().getRow(),(byte) 0);
			} catch (CollisionException | InvalidArgException e) {
				showBlock();
			}
		
		
	}




	

}
