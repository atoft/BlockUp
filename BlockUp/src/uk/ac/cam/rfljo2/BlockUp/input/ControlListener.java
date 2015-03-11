package uk.ac.cam.rfljo2.BlockUp.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This interface defines all the possible control options that there are in the game.
 * All of the inputs are overridden in an anonymous class in GameManager.
 * 
 * @author Tom Read-Cutting
 *
 */
public abstract class ControlListener implements ActionListener {
	
	//for the actionlistener functionality, as long as each string is unique, it doesn't matter what they are defined as.
	public static final String ROTATE_BLOCK_LEFT = "rbl";
	public static final String ROTATE_BLOCK_RIGHT = "rbr";
	public static final String MOVE_BLOCK_LEFT = "mbl";
	public static final String MOVE_BLOCK_RIGHT = "mbr";
	public static final String ROTATE_BLOCK_180 = "rb180";
	public static final String SPEED_UP_BLOCK = "sub";
	public static final String SLAM_BLOCK = "slb";
	public static final String PAUSE_GAME = "pgm";
	public static final String NAVIGATE_UP = "nup";
	public static final String NAVIGATE_DOWN = "ndo";
	public static final String NAVIGATE_LEFT = "nle";
	public static final String NAVIGATE_RIGHT = "nri";
	public static final String ENTER = "ent";
	public static final String GO_BACK = "gob";
	
	public abstract void rotateBlockLeft();
	
	public abstract void rotateBlockRight();
	
	public abstract void moveBlockLeft();
	
	public abstract void moveBlockRight();
	
	public abstract void rotateBlock180();
	
	public abstract void speedUpBlock();
	
	public abstract void slamBlock();
	
	public abstract void pauseGame();
	
	public abstract void navigateUp();
	
	public abstract void navigateDown();
	
	public abstract void navigateLeft();
	
	public abstract void navigateRight();
	
	public abstract void enter();
	
	public abstract void goBack();

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case ROTATE_BLOCK_LEFT:
				rotateBlockLeft();
			break;
			case ROTATE_BLOCK_RIGHT:
				rotateBlockRight();
			break;
			case MOVE_BLOCK_LEFT:
				moveBlockLeft();
			break;
			case MOVE_BLOCK_RIGHT:
				moveBlockRight();
			break;
			case ROTATE_BLOCK_180:
				rotateBlock180();
			break;
			case SPEED_UP_BLOCK:
				speedUpBlock();
			break;
			case SLAM_BLOCK:
				slamBlock();
			break;
			case PAUSE_GAME:
				pauseGame();
			break;
			case NAVIGATE_UP:
				navigateUp();
			break;
			case NAVIGATE_DOWN:
				navigateDown();
			break;
			case NAVIGATE_LEFT:
				navigateLeft();
			break;
			case NAVIGATE_RIGHT:
				navigateRight();
			break;
			case ENTER:
				enter();
			break;
			case GO_BACK:
				goBack();
			break;
		}
	}
	
}
