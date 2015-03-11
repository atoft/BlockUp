package uk.ac.cam.rfljo2.BlockUp.blocks;
import uk.ac.cam.rfljo2.BlockUp.*;


/**
 * Represents an L Block in BlockUp
 *
 */
public class LBlock extends Block {
	
	public LBlock() {
		Piece[] pieces = new Piece[4];
		pieces[0] = new Piece(0, 0, PieceType.ORANGE);
		pieces[1] = new Piece(-1, 0, PieceType.ORANGE);
		pieces[2] = new Piece(1, 0, PieceType.ORANGE);
		pieces[3] = new Piece(1, -1, PieceType.ORANGE);
		this.setPieces(pieces);
	}
}
