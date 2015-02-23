package uk.ac.cam.rfljo2.BlockUp;

/**
 * Line Block
 * @author Robin Otter
 *
 */
public class LineBlock extends Block {
	
	
	
	public LineBlock(GameBoard b) {
		super(b);
		setBlockType((byte) 1);
		setCells();
	}

	

	

	@Override
	public void place(int col, int row, byte rotationState) throws CollisionException, InvalidArgException {
		if (rotationState < 0 || rotationState > 1) throw new InvalidArgException();
		Cell c = new Cell(col, row);
		if (rotationState == 0) {
			if (getPivotPoint() != null) hideBlock();
			for (int i = -2; i < 2; i++) {
				if (mBoard.getCell(col + i, row) != 0) throw new CollisionException();
			}
			
			
			setPivotPoint(c);
			this.setRotationState((byte) 0);
			setCells();
			this.showBlock();
		} else {
			if (getPivotPoint() != null) hideBlock();
			
			for (int i = -2; i < 2; i++) {
				if (mBoard.getCell(col, row + i) != 0) throw new CollisionException();
			}
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



	@Override
	public void setCells() {
		Cell[] cells = new Cell[4];
		if (getRotationState() == 0) {
			for (int i = 0; i < 4; i++) {
				Cell c = new Cell(getPivotPoint().getCol() - 2 + i,getPivotPoint().getRow());
				cells[i] = c;
			}
		}
		if (getRotationState() == 1) {
			for (int i = 0; i < 4; i++) {
				Cell c = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow() - 2 + i);
				cells[i] = c;
			}
			
		}
		mCells = cells;
		
		
	}




	

}
