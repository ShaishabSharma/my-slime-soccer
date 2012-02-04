/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slimesoccer.framework;

import java.awt.Dimension;
import java.awt.Graphics2D;
import slimesoccer.input.InputManager;

/**
 *
 * @author &{user}
 */
public abstract class GameState {
    
    protected Dimension gameDims;
    protected InputManager input;
    protected boolean done = false;
    
    public GameState(Dimension gameDims,InputManager input){
	this.gameDims = gameDims;
	this.input = input;
    }
    
    public abstract void update(long elapsedTime);
    
    public abstract void draw(Graphics2D g);
    
    public boolean isDone(){
	return done;
    }
    
    public void finish(){
	done = true;
    }
    
    public void reset(){
	done = false;
    }
}
