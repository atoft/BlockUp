package uk.ac.cam.rfljo2.BlockUp;

/**
 * Line Block
 * @author Robin Otter
 *
 */
public class Squiggly extends Block {

	
	
	public Squiggly(GameBoard b) {
		super(b);
		setBlockType((byte) 5);
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
			cells[2] = new Cell(getPivotPoint().getCol() - 1,getPivotPoint().getRow() - 1);
			cells[3] = new Cell(getPivotPoint().getCol() + 1,getPivotPoint().getRow());
		}
		if (getRotationState() == 1) {
			for (int i = 0; i < 2; i++) {
				Cell c = new Cell(getPivotPoint().getCol() - i,getPivotPoint().getRow());
				cells[i] = c;
			}
			cells[2] = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow() - 1);
			cells[3] = new Cell(getPivotPoint().getCol() - 1,getPivotPoint().getRow() + 1);
		}
		
		mCells = cells;
		
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
