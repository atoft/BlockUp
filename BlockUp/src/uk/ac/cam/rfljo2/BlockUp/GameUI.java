package uk.ac.cam.rfljo2.BlockUp;

import javax.swing.*;	//TODO: Only import libraries which are needed

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import javax.swing.Timer;

/**
 * @Question Should GameManager and GameUI be integrated into the same Class?
 * 
 * Handles the GUI of the game in addition to detecting keyboard inputs.
 * 
 * @author Robin Otter
 * @author Alastair Toft
 *
 */
public class GameUI extends JFrame{
	
	public GameViewScreen viewScreen;
	public GameNextScreen nextBlockScreen;
	private static GameManager gameManager;
	
	
	
	public GameUI(){
		super(Strings.GAME_NAME);				//Calls the parent constructor, setting the window title
		
		
		
		setSize(500,680);
		setDefaultCloseOperation(EXIT_ON_CLOSE); //TODO: Probably not the desired operation, replace this later
		
		setLayout(new BorderLayout());
		
		//New window components are created, then positioned in the window
		JComponent viewPanel = Box.createVerticalBox();	
		addBorder(viewPanel, Strings.GUI_VIEW_LABEL);
		add(viewPanel,BorderLayout.CENTER);
		
		viewScreen = new GameViewScreen();	//Creates an instance of our custom ViewScreen class
		viewPanel.add(viewScreen);
		nextBlockScreen = new GameNextScreen();
		
		gameManager = new GameManager(this);
		
		JComponent sidePanel = Box.createVerticalBox();
		add(sidePanel,BorderLayout.EAST);
		
		JComponent nextBlockPanel = Box.createVerticalBox();
		
		sidePanel.add(nextBlockPanel);
		addBorder(nextBlockPanel, Strings.GUI_NEXT_BLOCK_LABEL);
		nextBlockPanel.add(nextBlockScreen);
		
		
		
		JComponent scorePanel = new JPanel();
		addBorder(scorePanel, Strings.GUI_SCORES_LABEL);
		sidePanel.add(scorePanel);
		
		JLabel text = new JLabel("Scores and such");
		scorePanel.add(text);
		
		JComponent optionPanel = new JPanel();
		addBorder(optionPanel, Strings.GUI_OPTIONS_LABEL);
		sidePanel.add(optionPanel);
		
		//Map key presses to Actions, and use the actions to send inputs to the GameManager object
	    viewPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0), "down");
	    viewPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0), "up");
	    viewPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0), "left");
	    viewPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0), "right");
	    
	    viewPanel.getActionMap().put("down", new AbstractAction(){
	    	public void actionPerformed(ActionEvent event){
	    		gameManager.downInput();
	    	}
	    });
	    viewPanel.getActionMap().put("up", new AbstractAction(){
	    	public void actionPerformed(ActionEvent event){
	    		gameManager.upInput();
	    	}
	    });
	    viewPanel.getActionMap().put("left", new AbstractAction(){
	    	public void actionPerformed(ActionEvent event){
	    		gameManager.leftInput();
	    	}
	    });
	    viewPanel.getActionMap().put("right", new AbstractAction(){
	    	public void actionPerformed(ActionEvent event){
	    		gameManager.rightInput();
	    	}
	    });
	}
	
	
	/**
	 * Calls the update method of the GameViewScreen object in order to display
	 * the board.
	 * 
	 * @param currentBoard The game board to display
	 */
	public void refreshScreen(GameBoard currentBoard){
		viewScreen.updateView(currentBoard);
	}
	
	
	
	/**
	 * Calls the update method of the GameNextScreen object in order to display
	 * the next block to be made.
	 * 
	 * @param id The integer corresponding to this block
	 */	
	public void refreshBlock(int id){
		nextBlockScreen.updateNext(id);
	}
	
	/**
	 * Draws a border around a window component
	 * @param component The object to be bordered
	 * @param title The title displayed on the border
	 */
	private void addBorder(JComponent component, String title){
		Border etch = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border tb = BorderFactory.createTitledBorder(etch,title);
		component.setBorder(tb);
	}
	
}

