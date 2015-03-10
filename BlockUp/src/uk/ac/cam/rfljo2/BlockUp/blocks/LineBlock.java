package uk.ac.cam.rfljo2.BlockUp.blocks;
import uk.ac.cam.rfljo2.BlockUp.*;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Line Block
 * @author Robin Otter
 *
 */
public class LineBlock extends Block {
	public LineBlock() {
		Piece[] pieces = new Piece[4];
		pieces[0] = new Piece(0, 0, BlockType.ORANGE);
		pieces[1] = new Piece(1, 0, BlockType.ORANGE);
		pieces[2] = new Piece(-1, 0, BlockType.ORANGE);
		pieces[3] = new Piece(-2, 0, BlockType.ORANGE);
		this.setPieces(pieces);
	}
}
