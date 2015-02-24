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
