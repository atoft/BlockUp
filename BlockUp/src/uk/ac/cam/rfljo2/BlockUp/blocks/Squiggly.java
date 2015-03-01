package uk.ac.cam.rfljo2.BlockUp.blocks;
import uk.ac.cam.rfljo2.BlockUp.*;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Line Block
 * @author Robin Otter
 *
 */
public class Squiggly extends Block {

	
	
	public Squiggly(GameBoard b) {
		super(b,(byte) 5);
		Queue<Byte> q = new LinkedList<Byte>();
		q.add((byte) 1);
		q.add((byte) 0);
		this.setRotationQueue(q);
	}

	
	@Override
	public void regenerateCells() {
		Cell[] cells = this.getCells();
		if (getRotationState() == 0) {
			for (int i = 0; i < 2; i++) {
				Cell c = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow() - i, cells[i].getType());
				cells[i] = c;
			}
			cells[2] = new Cell(getPivotPoint().getCol() - 1,getPivotPoint().getRow() - 1,cells[2].getType());
			cells[3] = new Cell(getPivotPoint().getCol() + 1,getPivotPoint().getRow(), cells[3].getType());
		}
		if (getRotationState() == 1) {
			for (int i = 0; i < 2; i++) {
				Cell c = new Cell(getPivotPoint().getCol() - i,getPivotPoint().getRow(),cells[i].getType());
				cells[i] = c;
			}
			cells[2] = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow() - 1,cells[2].getType());
			cells[3] = new Cell(getPivotPoint().getCol() - 1,getPivotPoint().getRow() + 1,cells[3].getType());
		}
		
		this.setCells(cells);
		
	}









	

}
