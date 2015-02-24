package uk.ac.cam.rfljo2.BlockUp.input;

/**
 * This interface defines all the possible control options that there are in the game.
 * All of the inputs are overridden in an anonymous class in GameManager.
 * 
 * @author Tom Read-Cutting
 *
 */
public interface ControlListener {
	
	public void rotateBlockLeft();
	
	public void rotateBlockRight();
	
	public void moveBlockLeft();
	
	public void moveBlockRight();
	
	public void rotateBlock180();
	
	public void speedUpBlock();
	
	public void slamBlock();
	
	public void pauseGame();
	
	public void navigateUp();
	
	public void navigateDown();
	
	public void navigateLeft();
	
	public void navigateRight();
	
	public void enter();
	
	public void goBack();
	
}
