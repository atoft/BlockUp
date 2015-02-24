package uk.ac.cam.rfljo2.BlockUp;

import java.util.LinkedList;
import java.util.Queue;

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
		Queue<Byte> q = new LinkedList<Byte>();
		q.add((byte) 1);
		q.add((byte) 0);
		this.setRotationQueue(q);
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
