package uk.ac.cam.rfljo2.BlockUp;

import java.awt.Graphics;

import javax.swing.JPanel;

import uk.ac.cam.rfljo2.BlockUp.blocks.Block;

/**
 * A class which renders the next block. Displays grid rows and
 * prints blocks in colour.
 * @author Robin Otter
 * @author Alastair Toft
 */
public class GameNextScreen extends GameViewScreen {
	
	
	private Block mNextBlock;
	
	public GameNextScreen(int width, int height){
		super(width,height);
	}
	
	/*@Override*/
	/**
	 * Overrides the JPanel inbuilt paintComponent to draw the game grid.
	 * First fills in the background colour, then loops over the array to
	 * draw blocks, before finally drawing grid lines.
	 */
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	/**
	 * Given the new version of the game grid, refreshes the displayed view.
	 * @Question How will the currently falling block be passed to this method?
	 * 
	 * @param currentBoard The board to display
	 */
	public void updateNext(Block next, GameBoard nextBoard){
		mNextBlock = next;
		this.setBoard(nextBoard);
		nextBoard.clearAllRows();
		try {
			mNextBlock.place(2, 2,(byte) 0,nextBoard);
		} catch (InvalidArgException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();
	}
}
