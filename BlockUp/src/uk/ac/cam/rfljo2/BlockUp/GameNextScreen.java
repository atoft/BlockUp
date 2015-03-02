package uk.ac.cam.rfljo2.BlockUp;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import uk.ac.cam.rfljo2.BlockUp.blocks.Block;

/**
 * A class which renders the next block. Displays grid rows and
 * prints blocks in colour.
 * @author Robin Otter
 * @author Alastair Toft
 */
public class GameNextScreen extends JPanel{
	private int mBoardWidth = 4;
	private int mBoardHeight = 4;
	private int mCellSize = 30;
	private GameBoard mNextBoard;
	
	private Block mNextBlock;
	
	@Override
	/**
	 * Overrides the JPanel inbuilt paintComponent to draw the game grid.
	 * First fills in the background colour, then loops over the array to
	 * draw blocks, before finally drawing grid lines.
	 */
	protected void paintComponent(Graphics g){
		
		int width = mBoardWidth * mCellSize;		//Calculate the size of the board and fill it with a background colour
		int height = mBoardHeight * mCellSize;
		g.setColor(java.awt.Color.black);
		g.fillRect(0,0,width,height);
		
		if(mNextBoard==null) return;
		
		for(int row=0; row<mBoardHeight;row++){//Loop over all cells in the board array
			for(int col=0; col<mBoardWidth;col++){
				if(mNextBoard.getCell(col,row)!=0){
					g.setColor(mNextBoard.getColour(col, row));
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
		
	}
	/*
	@Override
	/**
	 * Overrides the JPanel inbuilt paintComponent to draw the game grid.
	 * First fills in the background colour, then loops over the array to
	 * draw blocks, before finally drawing grid lines.
	 
	protected void paintComponent(Graphics g){
		
		int width = mBoardWidth * mCellSize;		//Calculate the size of the board and fill it with a background colour
		int height = mBoardHeight * mCellSize;
		g.setColor(java.awt.Color.black);
		g.fillRect(0,0,width,height);
		Color blockColour;
		int[][] blockShape;
		switch(mNextId){		//Colours of blocks are defined here
								//TODO Find a better way to get the block shape from the block object
			case 1: blockColour = java.awt.Color.cyan;
			blockShape = new int[][]	{{0,0,0,0},
										{1,1,1,1},
										{0,0,0,0},
										{0,0,0,0}};
			break;
			case 2: blockColour = java.awt.Color.blue;
			blockShape = new int[][]	{{0,0,0,0},
										{1,0,0,0},
										{1,1,1,0},
										{0,0,0,0}};
			break;
			case 3: blockColour = java.awt.Color.orange;
			blockShape = new int[][]	{{0,0,0,0},
										{1,1,1,0},
										{1,0,0,0},
										{0,0,0,0}};
			break;
			case 4: blockColour = java.awt.Color.yellow;
			blockShape = new int[][]	{{0,0,0,0},
										{0,1,1,0},
										{0,1,1,0},
										{0,0,0,0}};
			break;
			case 5: blockColour = java.awt.Color.green;
			blockShape = new int[][]	{{0,0,0,0},
										{1,1,0,0},
										{0,1,1,0},
										{0,0,0,0}};
			break;
			case 6: blockColour = java.awt.Color.magenta;
			blockShape = new int[][]	{{0,0,0,0},
										{0,1,0,0},
										{1,1,1,0},
										{0,0,0,0}};
			break;
			default : blockColour = java.awt.Color.red;
			blockShape = new int[][]	{{0,0,0,0},
										{0,1,1,0},
										{1,1,0,0},
										{0,0,0,0}};
			break;
		}
		
		for(int row=0; row<mBoardHeight;row++){//Loop over all cells in the board array
			for(int col=0; col<mBoardWidth;col++){
				if(blockShape[row][col]!=0){
					
					
					g.setColor(blockColour);
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
	}
	*/
	
	/**
	 * Given the new version of the game grid, refreshes the displayed view.
	 * @Question How will the currently falling block be passed to this method?
	 * 
	 * @param currentBoard The board to display
	 */
	public void updateNext(Block next, GameBoard nextBoard){
		mNextBlock = next;
		mNextBoard = nextBoard;
		try {
			mNextBlock.place(2, 2,(byte) 0);
		} catch (InvalidArgException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();
	}
}
