package uk.ac.cam.rfljo2.BlockUp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import uk.ac.cam.rfljo2.BlockUp.input.ControlListener;
import uk.ac.cam.rfljo2.BlockUp.blocks.*;


/**
 * @Question Should GameManager and GameUI be integrated into the same Class?
 * 
 * Represents the main gameplay loop and includes references to a UI to display the game
 * and a GameBoard to contain the board.
 * 
 * Receives inputs via the leftInput(), rightInput() etc methods which allow for
 * control of blocks.
 * @author Robin Otter
 * @author Alastair Toft
 */
public class GameManager {
	
	private int timeDelay = 500;
	public GameUI output;
	private GameBoard mainBoard;
	private GameBoard nextBoard;
	private boolean isPaused;
	private Block activeBlock;	// reference to the block on the board that is active and can be moved
	private Block nextBlock;	// the block which will be added once the current block lands
	
	private Timer playTimer = new Timer(timeDelay, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//Do something
			
			activeBlock.moveUp(mainBoard);
			output.refreshScreen(mainBoard);
			placementCheck();
		}
	});
	
	
	/**
	 * If the current block has landed, clear any full rows and
	 * spawn the next block.
	 */
	private void placementCheck(){
		if (activeBlock.isFinallyPlaced()){ 
			List<Integer> clearedRows = mainBoard.clearFullRows();
			if(clearedRows!=null){
				output.viewScreen.flashBlocks(mainBoard, clearedRows);
				/*
				 * An initial attempt at displaying a flashing animation when the rows are cleared.
				 * Doesn't behave properly because the game continues while the animation is
				 * playing. TODO: Need a way to stop pause execution until the animation is done.
				 */
			}
			spawnNextBlock();
			
		}
	}
	
	public GameManager(GameUI g){
		output = g;
		mainBoard = new GameBoard(GameConstants.BOARD_HEIGHT,GameConstants.BOARD_WIDTH);
		nextBoard = new GameBoard(GameConstants.NEXT_VIEW_HEIGHT,GameConstants.NEXT_VIEW_WIDTH);
		spawnNextBlock();
		playTimer.start();
		output.refreshScreen(mainBoard);
	}
	
	/**
	 * Randomly generates a new block and updates the NextBlock display
	 * with its appearance.
	 * @param board The GameBoard to which the block belongs.
	 * @return A reference to the new Block object.
	 */
	public Block generateBlock(){
		Random blockGen = new Random();
		int nextBlock = blockGen.nextInt(7);
		Block result;
		switch(nextBlock){
			case 0:		result = new LineBlock();
				break;
			case 1:		result = new ReverseLBlock();
				break;
			case 2:		result = new LBlock();
				break;
			case 3:		result = new Square();
				break;
			case 4:		result = new Squiggly();
				break;
			case 5:		result = new TBlock();
				break;
			default:	result = new ReverseSquiggly();
		}
		
		//result.makePowerBlock();
		
		return result;
	}
	/**
	 * Makes the next block into the active block and places it in the board.
	 * A random block is generated to become the next block.
	 */
	public void spawnNextBlock(){
		if(nextBlock != null){
			activeBlock = nextBlock;
			nextBlock = generateBlock();
			
		}
		else{
			activeBlock = generateBlock();
			nextBlock = generateBlock();
		}
		output.updateNextBlockScreen(nextBlock,nextBoard);
		try {
			//if (board.getActiveBlock().getBlockType() == 3 || board.getActiveBlock().getBlockType() == 5) board.getActiveBlock().place(4, 18, (byte)0);
			//else{ 
				activeBlock.setPivotPoint(null);
				boolean success = activeBlock.place(4, 18, (byte)0,mainBoard);	//TODO: Some blocks appear to place lower than others, due to pivots or something?
				if(!success) gameOver();	//If success is false, the block cannot fit in the board, so the game ends.
			//}
		} catch (InvalidArgException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * If the game is running, pauses the game. If it is paused, resumes the game.
	 */
	public void pause(){
		if(!isPaused){
			playTimer.stop();
			isPaused = true;
			output.setViewPaused(true);
		}
		else{
			playTimer.start();
			isPaused = false;
			output.setViewPaused(false);
		}
	}
	
	/**
	 * Freezes the game and calls the UI's game over display.
	 */
	public void gameOver(){
		pause();
		output.showGameOver();
	}
	
	public void restartGame(){
		//TODO: Reset scores
		mainBoard.clearAllRows();
		playTimer.restart();
		output.refreshScreen(mainBoard);
		if(isPaused){
			pause();
		}
	}
	
	/**
	 * Method which returns the control listener.
	 * First implementation.
	 * 
	 * @return
	 */
	public ControlListener getControlListener() {
		return new ControlListener() {

			@Override
			public void rotateBlockLeft() {
				if(!isPaused){
					activeBlock.rotateClockwise(mainBoard);
					output.refreshScreen(mainBoard);
				}
			}

			@Override
			public void rotateBlockRight() {
				
			}

			@Override
			public void moveBlockLeft() {
				if(!isPaused){
					activeBlock.moveLeft(mainBoard);
					output.refreshScreen(mainBoard);
				}
			}

			@Override
			public void moveBlockRight() {
				if(!isPaused){
					activeBlock.moveRight(mainBoard);
					output.refreshScreen(mainBoard);
				}
			}

			@Override
			public void rotateBlock180() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void speedUpBlock() {
				if(!isPaused){
					activeBlock.moveUp(mainBoard);
					playTimer.restart();
					output.refreshScreen(mainBoard);
					placementCheck();
				}
			}

			@Override
			public void slamBlock() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void pauseGame() {
				
				pause();
			}

			@Override
			public void navigateUp() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void navigateDown() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void navigateLeft() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void navigateRight() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void enter() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void goBack() {
				// TODO Auto-generated method stub
				
			}	
		};
	}
	
	/**
	 * This is the main method for the whole package
	 * @param args Takes no arguments
	 */
	public static void main(String[] args){
		GameUI gui = new GameUI();
		gui.setVisible(true);
		
		
	}
		
}
