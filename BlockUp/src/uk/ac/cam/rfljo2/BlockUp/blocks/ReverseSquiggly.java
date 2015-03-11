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
		pieces[0] = new Piece(0, 0, PieceType.YELLOW);
		pieces[1] = new Piece(-1, 0, PieceType.YELLOW);
		pieces[2] = new Piece(0, 1, PieceType.YELLOW);
		pieces[3] = new Piece(1, 1, PieceType.YELLOW);
		this.setPieces(pieces);
	}
	
}
