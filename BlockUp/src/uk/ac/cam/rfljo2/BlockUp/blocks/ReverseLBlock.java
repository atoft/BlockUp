package uk.ac.cam.rfljo2.BlockUp.blocks;
import uk.ac.cam.rfljo2.BlockUp.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * TODO
 *
 */
public class ReverseLBlock extends Block {

	
	public ReverseLBlock(GameBoard b) {
		super(b);
		setBlockType((byte) 2);
		regenerateCells();
		Queue<Byte> q = new LinkedList<Byte>();
		q.add((byte) 1);
		q.add((byte) 2);
		q.add((byte) 3);
		q.add((byte) 0);
		this.setRotationQueue(q);
	}
	
	@Override
	public void regenerateCells() {
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
		
		this.setCells(cells);
		
	}
	










	

}