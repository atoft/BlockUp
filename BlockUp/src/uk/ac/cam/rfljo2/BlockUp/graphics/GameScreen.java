package uk.ac.cam.rfljo2.BlockUp.graphics;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import uk.ac.cam.rfljo2.BlockUp.GameBoard;
import uk.ac.cam.rfljo2.BlockUp.Piece;
import uk.ac.cam.rfljo2.BlockUp.PieceType;
import uk.ac.cam.rfljo2.BlockUp.blocks.Block;



public abstract class GameScreen extends JPanel {

	private int mBoardWidth;
	private int mBoardHeight;
	private int mCellSize;
	private int flashDelay;
	private int numFlashes;
	private GameBoard mBoard;
	private boolean doneFlashing;
	private boolean flashState;
	private List<Integer> flashRows;
	
	public void setFlashState(boolean flashState) {
		this.flashState = flashState;
	}
	
	public void setFlashRows(List<Integer> flashRows) {
		this.flashRows = flashRows;
	}
	
	public GameScreen(int width, int height){
		mBoardWidth = width;
		mBoardHeight = height;
	}
	
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
		
		if(mBoard==null) return;
		
		for(int row=0; row<mBoardHeight;row++){//Loop over all cells in the board array
			for(int col=0; col<mBoardWidth;col++){
				
				byte currentType = mBoard.getCell(col,row);
				if(currentType!=0){
					g.setColor(PieceType.getColor(currentType));	//Convert the current cell type to a colo(u)r.
					g.fillRect(col*mCellSize,row*mCellSize,mCellSize,mCellSize);	//Fill the cell with the correct color.
				}
			}
		}
		
		Block activeBlock = mBoard.getActiveBlock();
		Piece[] blockPieces = activeBlock.getPieces();
		for(int i = 0; i < blockPieces.length; i++) {
			Piece bP = blockPieces[i];
			byte type = bP.getType();
			if(bP.getType() != 0) {
				g.setColor(PieceType.getColor(type));
				//fill in the rectangle at the correct offset
				g.fillRect((bP.getX()+activeBlock.getX())*mCellSize,(bP.getY()+activeBlock.getY())*mCellSize,mCellSize,mCellSize);	//Fill the cell with the correct color.
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

		//Flash rows
		if(flashState){
			for(int i : flashRows){
				g.setColor(java.awt.Color.white);
				g.fillRect(0,i*mCellSize,width,mCellSize);
			}
		}
	}
	
	/**
	 * Given the new version of the game grid, refreshes the displayed view.
	 * 
	 * @param currentBoard The board to display
	 */
	public void updateView(GameBoard currentBoard){
		mBoard = currentBoard;
		repaint();
	}
	
	/**
	 * Sets the Game Board without updating the screen.
	 * @param currentBoard
	 */
	public void setBoard(GameBoard currentBoard){
		mBoard = currentBoard;
	}

	/**
	 * TODO Doesnt work atm
	 * Updates the board and runs the flashing animation on a list of rows.
	 * @param currentBoard
	 * @param toFlash The list of rows over which to display the animation
	 */
	public void flashBlocks(GameBoard currentBoard, List<Integer>toFlash){
		setFlashRows(toFlash);
		
		
		Timer flashTimer = new Timer(flashDelay, new ActionListener() {
			int rep = 0;
			public void actionPerformed(ActionEvent e) {
				flashState = !flashState;
				updateView(mBoard);
		
			}
		});
		flashTimer.start();
	}
	
	
	
	
	
	
}
