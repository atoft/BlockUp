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
		pieces[0] = new Piece(0, 0, PieceType.GREEN);
		pieces[1] = new Piece(1, 0, PieceType.GREEN);
		pieces[2] = new Piece(0, -1, PieceType.GREEN);
		pieces[3] = new Piece(-1, -1, PieceType.GREEN);
		this.setPieces(pieces);
	}
}
