package uk.ac.cam.rfljo2.BlockUp.blocks;
import uk.ac.cam.rfljo2.BlockUp.*;

/**
 * TODO
 *
 */
public class ReverseLBlock extends Block {	
	public ReverseLBlock() {
		Piece[] pieces = new Piece[4];
		pieces[0] = new Piece(0, 0, PieceType.BLUE);
		pieces[1] = new Piece(-1, 0, PieceType.BLUE);
		pieces[2] = new Piece(1, 0, PieceType.BLUE);
		pieces[3] = new Piece(1, 1, PieceType.BLUE);
		this.setPieces(pieces);
	}
}
