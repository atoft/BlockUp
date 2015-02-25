package uk.ac.cam.rfljo2.BlockUp.input;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class KeyBoardControls extends ControlObserver {
	
	private ControlListener mCL;
	private JComponent test = new JPanel();
	
	
	public KeyBoardControls(JComponent test2) {
		test = test2;
		//Map key presses to Actions, and use the actions to send inputs to the GameManager object
		test.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0), "down");
		test.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0), "up");
		test.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0), "left");
		test.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0), "right");
		test.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P,0), "pause");
	}

	@Override
	public void setListener(ControlListener cL) {
		mCL = cL;
		
		//associate the correct controllistener events with the correct key-bindings.
		test.getActionMap().put("down", new AbstractAction(){
	    	public void actionPerformed(ActionEvent event){
	    		mCL.navigateDown();
	    		mCL.rotateBlockLeft();
	    	}
	    });
		
		test.getActionMap().put("up", new AbstractAction(){
	    	public void actionPerformed(ActionEvent event){
	    		mCL.navigateUp();
	    		mCL.speedUpBlock();
	    	}
	    });
		
		test.getActionMap().put("left", new AbstractAction(){
	    	public void actionPerformed(ActionEvent event){
	    		mCL.navigateLeft();
	    		mCL.moveBlockLeft();
	    	}
	    });
		
		test.getActionMap().put("right", new AbstractAction(){
	    	public void actionPerformed(ActionEvent event){
	    		mCL.navigateRight();
	    		mCL.moveBlockRight();
	    	}
	    });
		
		test.getActionMap().put("pause", new AbstractAction(){
	    	public void actionPerformed(ActionEvent event){
	    		mCL.pauseGame();
	    	}
	    });
	}
	
	

}
