/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slimesoccer.framework;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JFrame;
import slimesoccer.input.InputManager;
import slimesoccer.util.NullRepaintManager;

/**
 * This class holds the view (JFrame) and initiates the
 * update/draw chains. Its superclass takes care of timing.
 * @author &{user}
 */
public class SlimeStateManager extends GameStateManager{

    //use JFrame for view
    private ScreenManager screenManager;
    
    //states
    private GameState gameplay;
    
    //constants
    private static final Dimension screen = new Dimension(1024,768);
    private static final DisplayMode dm = new DisplayMode(1024,768,16,DisplayMode.REFRESH_RATE_UNKNOWN);
    
    /**
     * Entry point.
     * @param args 
     */
    public static void main(String[] args){
	new SlimeStateManager().run();
    }
    
    public SlimeStateManager(){
	//switch to fullscreen
	screenManager = ScreenManager.getInstance();
	screenManager.setFullScreen(dm);
	
	//create window
	JFrame window = screenManager.getFullScreenWindow();
	window.setTitle("Connor's Slime Soccer");
	
	//make the window's content pane transparent - to use swing
	if(window.getContentPane() instanceof JComponent){
	    ((JComponent)window.getContentPane()).setOpaque(false);
	}
	
	//install null repaint manager - to use swing
	NullRepaintManager.install();

	//done setting up window
	
	//set up states
	InputManager i = new InputManager(screenManager.getFullScreenWindow());
	gameplay = new SlimeGameplayState(screen,i);
	
	//start in game play state
	currentState = gameplay;
    }
    
    public void draw() {
	//get window graphics
	Graphics2D g = screenManager.getGraphics();
	
	//draw current state to screen
	currentState.draw(g);
	g.dispose();
	
	//blit image
	screenManager.update();
    }
    
    /*
     * Called at end of program.
     */
    public void cleanUp(){
	screenManager.restoreScreen();
    }

    /**
     * This method is where the game state should be changed.
     * @return 
     */
    public GameState getGameState() {
	return null;
    }
}
