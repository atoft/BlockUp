package uk.ac.cam.rfljo2.BlockUp.blocks;
import uk.ac.cam.rfljo2.BlockUp.*;

/**
 * Line Block
 * @author Robin Otter
 *
 */
public class Square extends Block {

	
	public Square(GameBoard b) {
		super(b,(byte) 4);
	}

	@Override
	public void regenerateCells() {
		Cell[] cells = this.getCells();
				Cell c = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow(),cells[0].getType());
				Cell d = new Cell(getPivotPoint().getCol() + 1,getPivotPoint().getRow(),cells[1].getType());
				Cell e = new Cell(getPivotPoint().getCol(),getPivotPoint().getRow() - 1,cells[2].getType());
				Cell f = new Cell(getPivotPoint().getCol() + 1,getPivotPoint().getRow() - 1,cells[3].getType());
				cells[0] = c;
				cells[1] = d;
				cells[2] = e;
				cells[3] = f;

		this.setCells(cells);
		
	}	
	
	
	@Override
	public void rotateLeft() {
		// Square cannot rotate
	}







	

}
