package uk.ac.cam.rfljo2.BlockUp.blocks;
import java.util.LinkedList;
import java.util.Queue;

import uk.ac.cam.rfljo2.BlockUp.*;

/**
 * Line Block
 * @author Robin Otter
 *
 */
public class Square extends Block {

	
	public Square(GameBoard b) {
		super(b,(byte) 4);
		Queue<Byte> q = new LinkedList<Byte>();
		q.add((byte) 3);
		q.add((byte) 2);
		q.add((byte) 1);
		q.add((byte) 0);
		this.setRotationQueue(q);
	}

	@Override
	public void regenerateCells() {
		Cell[] cells = this.getCells();
		if (getRotationState() == 0) {	
				Cell c = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow(),cells[0].getType());
				Cell d = new Cell(getPivotPoint().getCol() + 1,getPivotPoint().getRow(),cells[1].getType());
				Cell e = new Cell(getPivotPoint().getCol() + 1,getPivotPoint().getRow() - 1,cells[2].getType());
				Cell f = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow() - 1,cells[3].getType());
				
				cells[0] = c;
				cells[1] = d;
				cells[2] = e;
				cells[3] = f;
		}
		if (getRotationState() == 1) {	
			Cell f = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow(),cells[3].getType());
			Cell c = new Cell(getPivotPoint().getCol() + 1,getPivotPoint().getRow(),cells[0].getType());
			Cell d = new Cell(getPivotPoint().getCol() + 1,getPivotPoint().getRow() - 1,cells[1].getType());
			Cell e = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow() - 1,cells[2].getType());
			
			cells[0] = c;
			cells[1] = d;
			cells[2] = e;
			cells[3] = f;
		}
		if (getRotationState() == 2) {	
			Cell e = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow(),cells[2].getType());
			Cell f = new Cell(getPivotPoint().getCol() + 1,getPivotPoint().getRow(),cells[3].getType());
			Cell c = new Cell(getPivotPoint().getCol() + 1,getPivotPoint().getRow() - 1,cells[0].getType());
			Cell d = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow() - 1,cells[1].getType());
			
			cells[0] = c;
			cells[1] = d;
			cells[2] = e;
			cells[3] = f;
		}
		if (getRotationState() == 3) {	
			Cell d = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow(),cells[1].getType());
			Cell e = new Cell(getPivotPoint().getCol() + 1,getPivotPoint().getRow(),cells[2].getType());
			Cell f = new Cell(getPivotPoint().getCol() + 1,getPivotPoint().getRow() - 1,cells[3].getType());
			Cell c = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow() - 1,cells[0].getType());
			
			cells[0] = c;
			cells[1] = d;
			cells[2] = e;
			cells[3] = f;
		}


		this.setCells(cells);
		
	}	
	








	

}
