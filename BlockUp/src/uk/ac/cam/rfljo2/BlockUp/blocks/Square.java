package uk.ac.cam.rfljo2.BlockUp.blocks;

import uk.ac.cam.rfljo2.BlockUp.*;

/**
 * Line Block
 * @author Robin Otter
 *
 */
public class Square extends Block {
	public Square() {
		Piece[] pieces = new Piece[4];
		pieces[0] = new Piece(0, 0, BlockType.YELLOW);
		pieces[1] = new Piece(-1, 0, BlockType.YELLOW);
		pieces[2] = new Piece(-1, -1, BlockType.YELLOW);
		pieces[3] = new Piece(0, -1, BlockType.YELLOW);
		this.setPieces(pieces);
	}
	
	/**
	 * The square rotates slightly differently
	 * to normal blocks.
	 * 
	 * Works by transforming the block so that it's centre is at 0, 0, rotating it and then transforming it back.
	 */
	@Override
	protected void rotate(int amount) {
		Piece[] pieces = this.getPieces();
		if(amount == 0) return;
		for(int i = 0; i < pieces.length; i++) {
			//have to double size of coordinate system to be able to apply transformation, as centre is at (-0.5, -0.5)
			int x = pieces[i].getX() * 2;
			int y = pieces[i].getY() * 2;
			x++;
			y++;
			//rotate piece after transforming to correct space and moving origin
			switch(amount) {
				case 1:
					int temp = x;
					x = -y;
					y = temp;
				break;
				case 2:
					x = -x;
					y = -y;
				break;
				case 3:
					int temp1 = x;
					x = y;
					y = -temp1;
				break;
			}
			x--;
			y--;
			y /= 2;
			x /= 2;
			pieces[i] = new Piece(x, y, pieces[i].getType());
		}
	}
}
