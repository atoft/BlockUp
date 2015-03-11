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
	
	public static final String ROTATE_BLOCK_LEFT = "r";
	public static final String ROTATE_BLOCK_RIGHT = "r";
	public static final String MOVE_BLOCK_LEFT = "r";
	public static final String MOVE_BLOCK_RIGHT = "r";
	public static final String ROTATE_BLOCK_180 = "r";
	public static final String SPEED_UP_BLOCK = "r";
	public static final String PAUSE_GAME = "p";
	
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
			case PAUSE_GAME:
				pauseGame();
			break;
		}
	}
	
}
