package uk.ac.cam.rfljo2.BlockUp.blocks;
import uk.ac.cam.rfljo2.BlockUp.*;


/**
 * Line Block
 * @author Robin Otter
 *
 */
public class Squiggly extends Block {
	public Squiggly() {
		Piece[] pieces = new Piece[4];
		pieces[0] = new Piece(0, 0, BlockType.MAGENTA);
		pieces[1] = new Piece(1, 0, BlockType.MAGENTA);
		pieces[2] = new Piece(0, -1, BlockType.MAGENTA);
		pieces[3] = new Piece(-1, -1, BlockType.MAGENTA);
		this.setPieces(pieces);
	}
}
