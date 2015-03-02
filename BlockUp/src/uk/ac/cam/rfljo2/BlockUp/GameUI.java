package uk.ac.cam.rfljo2.BlockUp;

import javax.swing.*;	//TODO: Only import libraries which are needed

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.Timer;

import uk.ac.cam.rfljo2.BlockUp.blocks.Block;
import uk.ac.cam.rfljo2.BlockUp.input.ControlListener;
import uk.ac.cam.rfljo2.BlockUp.input.KeyBoardControls;

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
	private JButton pauseButton;
	private static GameManager gameManager;
	
	
	
	public GameUI() {
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
		
		pauseButton = new JButton(Strings.GUI_OPTIONS_PAUSE);
		optionPanel.add(pauseButton);
		
		
		//the code which implements the keyboard controls using the listener
		//a bit hacky atm but works so good point to commit as I have to go now so can't finish it :(
		//can't rotate anymore
		ControlListener cL = gameManager.getControlListener();
		KeyBoardControls kBC = new KeyBoardControls(viewPanel);
		kBC.setListener(cL);
		
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
	 * Updates the appearance of the GUI to reflect the paused/unpaused state of the game.
	 * @param p The paused state to set (true = paused)
	 */
	public void setViewPaused(boolean p){
		if(p){
			pauseButton.setText(Strings.GUI_OPTIONS_RESUME);
		}
		else{
			pauseButton.setText(Strings.GUI_OPTIONS_PAUSE);
		}
	}
	
	/**
	 * Displays a Game Over window which provides options to restart
	 * or quit the game
	 */
	public void showGameOver(){
		Object[] options = {Strings.GUI_GAMEOVER_RESTART,Strings.GUI_GAMEOVER_QUIT};
		int n = JOptionPane.showOptionDialog(this, Strings.GUI_GAMEOVER_MESSAGE, 
				Strings.GUI_GAMEOVER_TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		//TODO: Display scores
		if(n==0){
			//TODO: Not happy with UI referencing GameManager and vice versa like this
			gameManager.restartGame();
			System.out.println("restart the game");
		}
		else{
			this.dispose();
		}
	}
	
	/**
	 * 
	 * TODO: Remove this once the next block screen has been improved
	 * Calls the update method of the GameNextScreen object in order to display
	 * the next block to be made.
	 * 
	 * @param id The integer corresponding to this block
	 */	
	public void refreshBlock(Block next, GameBoard nextBoard){
		nextBlockScreen.updateNext(next, nextBoard);
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

