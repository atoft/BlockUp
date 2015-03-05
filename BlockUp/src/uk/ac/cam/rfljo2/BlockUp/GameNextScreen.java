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
	
	
	//private int mBoardWidth = 4;
	//private int mBoardHeight = 4;
	//private int mCellSize = 30;
	//protected GameBoard mBoard;
	
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
		/*
		int width = mBoardWidth * mCellSize;		//Calculate the size of the board and fill it with a background colour
		int height = mBoardHeight * mCellSize;
		g.setColor(java.awt.Color.black);
		g.fillRect(0,0,width,height);
		
		if(mNextBoard==null) return;
		
		for(int row=0; row<mBoardHeight;row++){//Loop over all cells in the board array
			for(int col=0; col<mBoardWidth;col++){
				if(mNextBoard.getCell(col,row)!=0){
					g.setColor(BlockType.getColor(mNextBoard.getCell(col, row)));
					g.fillRect(col*mCellSize,row*mCellSize,mCellSize,mCellSize);
				}
			}
		}
		
		//Draw light gray lines onto the board
		g.setColor(java.awt.Color.LIGHT_GRAY);
		for(int x=0;x<(mBoardWidth);x++){
			g.drawLine(mCellSize*x,0,mCellSize*x,height);
		}
		for(int y=0;y<(mBoardHeight);y++){
			g.drawLine(0,mCellSize*y,width,mCellSize*y);
		}
		
	}*/
	
	/**
	 * Given the new version of the game grid, refreshes the displayed view.
	 * @Question How will the currently falling block be passed to this method?
	 * 
	 * @param currentBoard The board to display
	 */
	public void updateNext(Block next, GameBoard nextBoard){
		mNextBlock = next;
		setBoard(nextBoard);
		try {
			mNextBlock.place(2, 2,(byte) 0,nextBoard);
		} catch (InvalidArgException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();
	}
}
