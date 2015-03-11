package uk.ac.cam.rfljo2.BlockUp.blocks;
import uk.ac.cam.rfljo2.BlockUp.*;

/**
 * TODO
 *
 */
public class TBlock extends Block {
	public TBlock() {
		Piece[] pieces = new Piece[4];
		pieces[0] = new Piece(0, 0, PieceType.MAGENTA);
		pieces[1] = new Piece(0, 1, PieceType.MAGENTA);
		pieces[2] = new Piece(-1, 0, PieceType.MAGENTA);
		pieces[3] = new Piece(1, 0, PieceType.MAGENTA);
		this.setPieces(pieces);
	}
}
