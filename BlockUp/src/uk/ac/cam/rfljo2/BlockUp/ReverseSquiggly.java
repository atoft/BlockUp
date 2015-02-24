package uk.ac.cam.rfljo2.BlockUp;

import java.util.LinkedList;
import java.util.Queue;

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
		Queue<Byte> q = new LinkedList<Byte>();
		q.add((byte) 1);
		q.add((byte) 0);
		this.setRotationQueue(q);
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







	

}
