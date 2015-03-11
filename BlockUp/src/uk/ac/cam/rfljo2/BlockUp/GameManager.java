package uk.ac.cam.rfljo2.BlockUp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import uk.ac.cam.rfljo2.BlockUp.input.ControlListener;
import uk.ac.cam.rfljo2.BlockUp.blocks.*;


/**
 * Represents the main gameplay loop and includes references to a UI to display the game
 * and a GameBoard to contain the board.
 * 
 * Receives inputs via the leftInput(), rightInput() etc methods which allow for
 * control of blocks.
 * @author Robin Otter
 * @author Alastair Toft
 */
public class GameManager {
	
	public GameUI output;
	private GameBoard mainBoard;
	private GameBoard nextBoard;
	private boolean isPaused;
	private Block activeBlock;	// reference to the block on the board that is active and can be moved
	private Block nextBlock;	// the block which will be added once the current block lands
	
	
	/*
	 * This timer performs the regular falling motion of the block.
	 */
	private Timer playTimer = new Timer(GameConstants.GAME_TIMER_DELAY, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			moveActiveBlockUp();
			output.refreshScreen(mainBoard);
		}
	});
	
	
<<<<<<< HEAD
	public GameManager(){
		output = new GameUI(this);
		
		mainBoard = new GameBoard(GameConstants.BOARD_WIDTH,GameConstants.BOARD_HEIGHT);
		nextBoard = new GameBoard(GameConstants.NEXT_VIEW_WIDTH,GameConstants.NEXT_VIEW_HEIGHT);
=======
	/**
	 * If the current block has landed, clear any full rows and
	 * spawn the next block.
	 */
	private void placementCheck(){
		if (activeBlock.isFinallyPlaced()){ 
			//pause();
			List<Integer> clearedRows = mainBoard.clearFullRows();
			if(clearedRows!=null){
				output.viewScreen.flashBlocks(mainBoard, clearedRows);
			}
			spawnNextBlock();
			
		}
	}
	
	public GameManager(GameUI g){
		output = g;
		mainBoard = new GameBoard(GameConstants.BOARD_HEIGHT,GameConstants.BOARD_WIDTH);
		nextBoard = new GameBoard(GameConstants.NEXT_VIEW_HEIGHT,GameConstants.NEXT_VIEW_WIDTH);
>>>>>>> refs/remotes/origin/master
		spawnNextBlock();
		playTimer.start();
		output.refreshScreen(mainBoard);
		
		output.setVisible(true);
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
<<<<<<< HEAD

=======
>>>>>>> refs/remotes/origin/master
		return result;
	}
	
	
	/**
	 * Makes the next block into the active block and places it in the board.
	 * A random block is generated to become the next block.
	 */
	public void spawnNextBlock(){
		
		if(nextBlock != null){
			mainBoard.placeBlock(); //The current block is stored in the gameBoard's array
			mainBoard.clearFullRows(); //clear the full rows
			activeBlock = nextBlock;
			nextBlock = generateBlock();
			
		}
		else{	//The case in which the game has just started
			activeBlock = generateBlock();
			nextBlock = generateBlock();
		}
		
		output.updateNextBlockScreen(nextBlock,nextBoard);
<<<<<<< HEAD
		mainBoard.setActiveBlock(activeBlock);
		
		activeBlock.setX(GameConstants.BLOCK_SPAWN_POSITION_X);
		activeBlock.setY(GameConstants.BLOCK_SPAWN_POSITION_Y);
			
		if(mainBoard.collisionTest(GameConstants.BLOCK_SPAWN_POSITION_X, GameConstants.BLOCK_SPAWN_POSITION_Y)){
			//If the block collides in the initial position
			gameOver();
=======
		try {
				activeBlock.setPivotPoint(new Piece(-10, -10,(byte) 0));
				activeBlock.regenerateCells();
				boolean success = activeBlock.place(4, 18, (byte)0,mainBoard);	//TODO: Some blocks appear to place lower than others, due to pivots or something?
				if(!success) gameOver();	//If success is false, the block cannot fit in the board, so the game ends.
			//}
		} catch (InvalidArgException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
>>>>>>> refs/remotes/origin/master
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
	
	/**
	 * Empties all rows in the main game board, restarts the timer and begins the game again.
	 */
	public void restartGame(){
		//TODO: Reset scores
		mainBoard.clearAllRows();
		playTimer.restart();
		output.refreshScreen(mainBoard);
		if(isPaused){
			pause();
		}
	}
<<<<<<< HEAD
	
	/*
	 * Methods for moving the current block (which is not actually placed in the board
	 */
	
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
		if(mainBoard.collisionTest(activeBlock.getX(), activeBlock.getY()-1)==false){
			activeBlock.setY(activeBlock.getY()-1);
		}
		else{
			spawnNextBlock();
		}
		
	}	
	
	
	
=======
>>>>>>> refs/remotes/origin/master
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
	


	/**
	 * This is the main method for the whole package
	 * @param args Takes no arguments
	 */
	public static void main(String[] args){
		GameManager game = new GameManager();
		
		
	}
		
}
