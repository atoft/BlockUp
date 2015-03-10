package uk.ac.cam.rfljo2.BlockUp.blocks;
import uk.ac.cam.rfljo2.BlockUp.*;


/**
 * Line Block
 * @author Robin Otter
 *
 */
public class ReverseSquiggly extends Block {
	
	public ReverseSquiggly() {
		Piece[] pieces = new Piece[4];
		pieces[0] = new Piece(0, 0, BlockType.YELLOW);
		pieces[1] = new Piece(-1, 0, BlockType.YELLOW);
		pieces[2] = new Piece(0, 1, BlockType.YELLOW);
		pieces[3] = new Piece(1, 1, BlockType.YELLOW);
		this.setPieces(pieces);
	}
	
}
