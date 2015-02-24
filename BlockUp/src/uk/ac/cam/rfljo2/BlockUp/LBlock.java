package uk.ac.cam.rfljo2.BlockUp;

/**
 * Represents an L Block in BlockUp
 *
 */
public class LBlock extends Block {

	
	public LBlock(GameBoard b) {
		super(b);
		setBlockType((byte) 3);
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
			cells[3] = new Cell(getPivotPoint().getCol() + 1,getPivotPoint().getRow() + 1);
		}
		if (getRotationState() == 1) {
			for (int i = 0; i < 3; i++) {
				Cell c = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow() - 1 + i);
				cells[i] = c;
			}
			cells[3] = new Cell(getPivotPoint().getCol() + 1,getPivotPoint().getRow() - 1);
		}
		if (getRotationState() == 2) {
			for (int i = 0; i < 3; i++) {
				Cell c = new Cell(getPivotPoint().getCol() - 1 + i,getPivotPoint().getRow());
				cells[i] = c;
			}
			cells[3] = new Cell(getPivotPoint().getCol() - 1,getPivotPoint().getRow() - 1);
		}
		if (getRotationState() == 3) {
			for (int i = 0; i < 3; i++) {
				Cell c = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow() - 1 + i);
				cells[i] = c;
			}
			cells[3] = new Cell(getPivotPoint().getCol() - 1,getPivotPoint().getRow() + 1);
		}
		
		mCells = cells;
		
	}
	
	
	
	



	@Override
	public void rotate() {
		if (getRotationState() == 3)
			try {
				place(this.getPivotPoint().getCol(),this.getPivotPoint().getRow(),(byte) 0);
			} catch (CollisionException e) {
			} catch (InvalidArgException e) {
			}
		else
			try {
				place(this.getPivotPoint().getCol(),this.getPivotPoint().getRow(),(byte) (getRotationState() + 1));
			} catch (CollisionException | InvalidArgException e) {
				
			}
		
		
	}




	

}
