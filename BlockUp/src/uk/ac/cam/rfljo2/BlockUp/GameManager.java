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
			
			moveActiveBlockUp();
			output.refreshScreen(mainBoard);
		}
	});
	
	
	public GameManager(GameUI g){
		output = g;
		mainBoard = new GameBoard(GameConstants.BOARD_WIDTH,GameConstants.BOARD_HEIGHT);
		nextBoard = new GameBoard(GameConstants.NEXT_VIEW_WIDTH,GameConstants.NEXT_VIEW_HEIGHT);
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

		return result;
	}
	/**
	 * Makes the next block into the active block and places it in the board.
	 * A random block is generated to become the next block.
	 */
	public void spawnNextBlock(){
		
		
		
		if(nextBlock != null){
			mainBoard.placeBlock();
			activeBlock = nextBlock;
			nextBlock = generateBlock();
			
		}
		else{
			activeBlock = generateBlock();
			nextBlock = generateBlock();
		}
		output.updateNextBlockScreen(nextBlock,nextBoard);
		mainBoard.setActiveBlock(activeBlock);
		activeBlock.setX(GameConstants.BLOCK_SPAWN_POSITION_X);
		activeBlock.setY(GameConstants.BLOCK_SPAWN_POSITION_Y);
			
		if(mainBoard.collisionTest(GameConstants.BLOCK_SPAWN_POSITION_X, GameConstants.BLOCK_SPAWN_POSITION_Y)){
			gameOver();
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
					activeBlock.rotateClockwise();
					output.refreshScreen(mainBoard);
				}
			}

			@Override
			public void rotateBlockRight() {
				activeBlock.rotateAntiClockwise();
			}

			@Override
			public void moveBlockLeft() {
				if(!isPaused){
					moveActiveBlockLeft();
					output.refreshScreen(mainBoard);
				}
			}

			@Override
			public void moveBlockRight() {
				if(!isPaused){
					moveActiveBlockRight();
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
					moveActiveBlockUp();
					playTimer.restart();
					output.refreshScreen(mainBoard);
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
	
	protected void moveActiveBlockLeft() {
		if(mainBoard.collisionTest(activeBlock.getX()-1, activeBlock.getY())==false){
			activeBlock.setX(activeBlock.getX() - 1);
		}
	}

	protected void moveActiveBlockRight() {
		if(mainBoard.collisionTest(activeBlock.getX()+1, activeBlock.getY())==false){
			activeBlock.setX(activeBlock.getX() + 1);
		}
	}

	/**
	 * Moves the active block up, unless there is a collision, in which case a new block
	 * is spawned.
	 */
	protected void moveActiveBlockUp() {
		// TODO Auto-generated method stub
		if(mainBoard.collisionTest(activeBlock.getX(), activeBlock.getY()-1)==false){
			activeBlock.setY(activeBlock.getY()-1);
		}
		else{
			spawnNextBlock();
		}
		
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
