package uk.ac.cam.rfljo2.BlockUp;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;


/**
 * A class which renders the current state of the world. Displays grid rows and
 * prints blocks in colour.
 * @author Robin Otter
 * @author Alastair Toft
 */
public class GameViewScreen extends JPanel{

	private int mBoardWidth = 10;
	private int mBoardHeight = 20;
	private int mCellSize = 30;
	
	private GameBoard mCurrentBoard;
	
	
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
		
		if(mCurrentBoard==null) return;
		
		for(int row=0; row<mBoardHeight;row++){//Loop over all cells in the board array
			for(int col=0; col<mBoardWidth;col++){
				if(mCurrentBoard.getCell(col,row)!=0){
					Color blockColour;
					switch(mCurrentBoard.getCell(col, row)){		//Colours of blocks are defined here
						case 1: blockColour = java.awt.Color.cyan;
							break;
						case 2: blockColour = java.awt.Color.blue;
							break;
						case 3: blockColour = java.awt.Color.orange;
							break;
						case 4: blockColour = java.awt.Color.yellow;
							break;
						case 5: blockColour = java.awt.Color.green;
							break;
						case 6: blockColour = java.awt.Color.magenta;
							break;
						default : blockColour = java.awt.Color.red;
							break;
					}
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

	
	/**
	 * Given the new version of the game grid, refreshes the displayed view.
	 * @Question How will the currently falling block be passed to this method?
	 * 
	 * @param currentBoard The board to display
	 */
	public void updateView(GameBoard currentBoard){
		mCurrentBoard = currentBoard;
		repaint();
	}
}
