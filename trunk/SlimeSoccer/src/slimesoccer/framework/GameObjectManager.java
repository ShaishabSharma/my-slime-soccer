/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slimesoccer.framework;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author &{user}
 */
public abstract class GameObjectManager {
    
    protected ArrayList<GameObject> objects;
    protected Dimension gameDims;
 
    public GameObjectManager(Dimension gameDims){
	this.gameDims = gameDims;
	objects = new ArrayList<GameObject>();
    }
    
    public void add(GameObject o){
	objects.add(o);
    }
    
    public void remove(GameObject o){
	objects.remove(o);
    }
    
    public void remove(int i){
	objects.remove(i);
    }
    
    public void update(long elapsedTime){
	GameObject o;
	for(int i = 0; i < objects.size(); i++){
	    o = objects.get(i);
	    if(o.isExpired())remove(o);
	    else{
		updateObject(elapsedTime,o);
		o.update(elapsedTime);
	    }
	}
    }
    
    protected abstract void updateObject(long elapsedTime,GameObject o);
    
    public void draw(Graphics2D g){
	for(GameObject s: objects){
	    drawObject(g,s);
	}
    }
    
    protected void drawObject(Graphics2D g,GameObject o){
	    if(o.isOffScreen(gameDims.width,gameDims.height)){
		return;   //do not draw offscreen sprites
	    }
	    
	    //draw the sprite
	    o.draw(g);
    }

}
