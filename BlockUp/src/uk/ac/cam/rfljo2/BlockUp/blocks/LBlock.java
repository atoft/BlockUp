package uk.ac.cam.rfljo2.BlockUp.blocks;
import uk.ac.cam.rfljo2.BlockUp.*;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Represents an L Block in BlockUp
 *
 */
public class LBlock extends Block {

	
	public LBlock(GameBoard b) {
		super(b, BlockType.ORANGE);
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
			for (int i = 0; i < 3; i++) {
				Cell c = new Cell(getPivotPoint().getCol() - 1 + i,getPivotPoint().getRow(),cells[i].getType());
				cells[i] = c;
			}
			cells[3] = new Cell(getPivotPoint().getCol() + 1,getPivotPoint().getRow() + 1,cells[3].getType());
		}
		if (getRotationState() == 1) {
			for (int i = 0; i < 3; i++) {
				Cell c = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow() + 1 - i,cells[i].getType());
				cells[i] = c;
			}
			cells[3] = new Cell(getPivotPoint().getCol() + 1,getPivotPoint().getRow() - 1,cells[3].getType());
		}
		if (getRotationState() == 2) {
			for (int i = 0; i < 3; i++) {
				Cell c = new Cell(getPivotPoint().getCol() + 1 - i,getPivotPoint().getRow(),cells[i].getType());
				cells[i] = c;
			}
			cells[3] = new Cell(getPivotPoint().getCol() - 1,getPivotPoint().getRow() - 1,cells[3].getType());
		}
		if (getRotationState() == 3) {
			for (int i = 0; i < 3; i++) {
				Cell c = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow() - 1 + i,cells[i].getType());
				cells[i] = c;
			}
			cells[3] = new Cell(getPivotPoint().getCol() - 1,getPivotPoint().getRow() + 1,cells[3].getType());
		}
		
		this.setCells(cells);
		
	}
	
	
	
	






	

}
