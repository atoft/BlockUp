package uk.ac.cam.rfljo2.BlockUp;

/**
 * Line Block
 * @author Robin Otter
 *
 */
public class Square extends Block {

	
	public Square(GameBoard b) {
		super(b);
		setBlockType((byte) 4);
		regenerateCells();
	}

	@Override
	public void regenerateCells() {
		Cell[] cells = new Cell[4];
			for (int i = 0; i < 2; i++) {
				Cell c = new Cell(getPivotPoint().getCol() + i,getPivotPoint().getRow());
				Cell d = new Cell(getPivotPoint().getCol() + i,getPivotPoint().getRow() - 1);
				cells[i] = c;
				cells[i+2] = d;
			}

		this.setCells(cells);
		
	}	
	
	
	





	@Override
	public void rotateLeft() {
		// Square cannot rotate.
	}




	

}
