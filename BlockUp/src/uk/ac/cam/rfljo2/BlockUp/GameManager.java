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
	private GameBoard board;
	private boolean isPaused;
	
	private Timer playTimer = new Timer(timeDelay, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//Do something
			
			board.getActiveBlock().moveUp(board);
			output.refreshScreen(board);
			placementCheck();
		}
	});
	
	/**
	 * If the current block has landed, clear any full rows and
	 * spawn the next block.
	 */
	private void placementCheck(){
		if (board.getActiveBlock().isFinallyPlaced()){ 
			List<Integer> clearedRows = board.clearFullRows();
			if(clearedRows!=null){
				output.viewScreen.flashBlocks(board, clearedRows);
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
		board = new GameBoard(20,10);
		spawnNextBlock();
		
		playTimer.start();
		output.refreshScreen(board);
	}
	
	/**
	 * Randomly generates a new block and updates the NextBlock display
	 * with its appearance.
	 * @param board The GameBoard to which the block belongs.
	 * @return A reference to the new Block object.
	 */
	public Block generateBlock(GameBoard board){
		Random blockGen = new Random();
		int nextBlock = blockGen.nextInt(7);
		Block result;
		Block resultClone;
		GameBoard nextBoard = new GameBoard(4,4);
		switch(nextBlock){
			case 0:		result = new LineBlock(board);
						resultClone = new LineBlock(nextBoard);
				break;
			case 1:		result = new ReverseLBlock(board);
						resultClone = new ReverseLBlock(nextBoard);
				break;
			case 2:		result = new LBlock(board);
						resultClone = new LBlock(nextBoard);
				break;
			case 3:		result = new Square(board);
						resultClone = new Square(nextBoard);
				break;
			case 4:		result = new Squiggly(board);
						resultClone = new Squiggly(nextBoard);
				break;
			case 5:		result = new TBlock(board);
						resultClone = new TBlock(nextBoard);
				break;
			default:	result = new ReverseSquiggly(board);
						resultClone = new ReverseSquiggly(nextBoard);
		}
		
		//result.makePowerBlock();
		//resultClone.makePowerBlock();
		output.refreshBlock(resultClone,nextBoard);
		return result;
	}
	/**
	 * Makes the next block into the active block and places it in the board.
	 * A random block is generated to become the next block.
	 */
	public void spawnNextBlock(){
		if(board.getNextBlock()!=null){
			board.setActiveBlock(board.getNextBlock());
			board.setNextBlock(generateBlock(board));
		}
		else{
			board.setActiveBlock(generateBlock(board));
			board.setNextBlock(generateBlock(board));
		}
		try {
			//if (board.getActiveBlock().getBlockType() == 3 || board.getActiveBlock().getBlockType() == 5) board.getActiveBlock().place(4, 18, (byte)0);
			//else{ 
				boolean success = board.getActiveBlock().place(4, 18, (byte)0,board);	//TODO: Some blocks appear to place lower than others, due to pivots or something?
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
		board.clearAllRows();
		playTimer.restart();
		output.refreshScreen(board);
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
					board.getActiveBlock().rotateClockwise(board);
					output.refreshScreen(board);
				}
			}

			@Override
			public void rotateBlockRight() {
				
			}

			@Override
			public void moveBlockLeft() {
				if(!isPaused){
					board.getActiveBlock().moveLeft(board);
					output.refreshScreen(board);
				}
			}

			@Override
			public void moveBlockRight() {
				if(!isPaused){
					board.getActiveBlock().moveRight(board);
					output.refreshScreen(board);
				}
			}

			@Override
			public void rotateBlock180() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void speedUpBlock() {
				if(!isPaused){
					board.getActiveBlock().moveUp(board);
					playTimer.restart();
					output.refreshScreen(board);
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
