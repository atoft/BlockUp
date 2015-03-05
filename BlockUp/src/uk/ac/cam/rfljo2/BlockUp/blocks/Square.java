package uk.ac.cam.rfljo2.BlockUp.blocks;
import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

import uk.ac.cam.rfljo2.BlockUp.*;

/**
 * Line Block
 * @author Robin Otter
 *
 */
public class Square extends Block {

	
	public Square() {
		super(BlockType.YELLOW);
		Queue<Byte> q = new LinkedList<Byte>();
		q.add((byte) 3);
		q.add((byte) 2);
		q.add((byte) 1);
		q.add((byte) 0);
		this.setRotationQueue(q);
	}

	@Override
	public void regenerateCells() {
		Piece[] cells = this.getCells();
		if (getRotationState() == 0) {	
				Piece c = new Piece(getPivotPoint().getCol(),getPivotPoint().getRow(),cells[0].getType());
				Piece d = new Piece(getPivotPoint().getCol() + 1,getPivotPoint().getRow(),cells[1].getType());
				Piece e = new Piece(getPivotPoint().getCol() + 1,getPivotPoint().getRow() - 1,cells[2].getType());
				Piece f = new Piece(getPivotPoint().getCol(),getPivotPoint().getRow() - 1,cells[3].getType());
				
				cells[0] = c;
				cells[1] = d;
				cells[2] = e;
				cells[3] = f;
		}
		if (getRotationState() == 1) {	
			Piece f = new Piece(getPivotPoint().getCol(),getPivotPoint().getRow(),cells[3].getType());
			Piece c = new Piece(getPivotPoint().getCol() + 1,getPivotPoint().getRow(),cells[0].getType());
			Piece d = new Piece(getPivotPoint().getCol() + 1,getPivotPoint().getRow() - 1,cells[1].getType());
			Piece e = new Piece(getPivotPoint().getCol(),getPivotPoint().getRow() - 1,cells[2].getType());
			
			cells[0] = c;
			cells[1] = d;
			cells[2] = e;
			cells[3] = f;
		}
		if (getRotationState() == 2) {	
			Piece e = new Piece(getPivotPoint().getCol(),getPivotPoint().getRow(),cells[2].getType());
			Piece f = new Piece(getPivotPoint().getCol() + 1,getPivotPoint().getRow(),cells[3].getType());
			Piece c = new Piece(getPivotPoint().getCol() + 1,getPivotPoint().getRow() - 1,cells[0].getType());
			Piece d = new Piece(getPivotPoint().getCol(),getPivotPoint().getRow() - 1,cells[1].getType());
			
			cells[0] = c;
			cells[1] = d;
			cells[2] = e;
			cells[3] = f;
		}
		if (getRotationState() == 3) {	
			Piece d = new Piece(getPivotPoint().getCol(),getPivotPoint().getRow(),cells[1].getType());
			Piece e = new Piece(getPivotPoint().getCol() + 1,getPivotPoint().getRow(),cells[2].getType());
			Piece f = new Piece(getPivotPoint().getCol() + 1,getPivotPoint().getRow() - 1,cells[3].getType());
			Piece c = new Piece(getPivotPoint().getCol(),getPivotPoint().getRow() - 1,cells[0].getType());
			
			cells[0] = c;
			cells[1] = d;
			cells[2] = e;
			cells[3] = f;
		}


		this.setCells(cells);
		
	}	
	








	

}
