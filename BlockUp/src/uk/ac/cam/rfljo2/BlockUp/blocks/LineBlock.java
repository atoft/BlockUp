package uk.ac.cam.rfljo2.BlockUp.blocks;
import uk.ac.cam.rfljo2.BlockUp.*;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Line Block
 * @author Robin Otter
 *
 */
public class LineBlock extends Block {
	
	
	
	public LineBlock(GameBoard b) {
		super(b, BlockType.CYAN);
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
			for (int i = 0; i < 4; i++) {
				Cell c = new Cell(getPivotPoint().getCol() - 2 + i,getPivotPoint().getRow(),cells[i].getType());
				cells[i] = c;
			}
		}
		if (getRotationState() == 1) {
			for (int i = 0; i < 4; i++) {
				Cell c = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow() +1 - i,cells[i].getType());
				cells[i] = c;
			}
			
		}
		if (getRotationState() == 2) {
			for (int i = 0; i < 4; i++) {
				Cell c = new Cell(getPivotPoint().getCol() + 1 - i,getPivotPoint().getRow(),cells[i].getType());
				cells[i] = c;
			}
		}
		if (getRotationState() == 3) {
			for (int i = 0; i < 4; i++) {
				Cell c = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow() - 2 + i,cells[i].getType());
				cells[i] = c;
			}
			
		}
		this.setCells(cells);
		
		
	}




	

}
