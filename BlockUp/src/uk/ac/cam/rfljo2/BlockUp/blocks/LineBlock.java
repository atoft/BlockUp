package uk.ac.cam.rfljo2.BlockUp.blocks;
import uk.ac.cam.rfljo2.BlockUp.*;

/**
 * Line Block
 * @author Robin Otter
 *
 */
public class LineBlock extends Block {
	public LineBlock() {
		Piece[] pieces = new Piece[4];
		pieces[0] = new Piece(0, 0, BlockType.BLUE);
		pieces[1] = new Piece(1, 0, BlockType.BLUE);
		pieces[2] = new Piece(-1, 0, BlockType.BLUE);
		pieces[3] = new Piece(-2, 0, BlockType.BLUE);
		this.setPieces(pieces);
	}
}
