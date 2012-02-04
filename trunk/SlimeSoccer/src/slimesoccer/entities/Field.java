/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slimesoccer.entities;

import java.awt.Dimension;
import java.awt.Graphics2D;
import slimesoccer.framework.GameObject;
import slimesoccer.framework.GameObjectManager;
import slimesoccer.util.Vector2D;

/**
 * This class defines a basic field.
 * Different field types may be used besides the soccer field.
 * The field contains the sprites, namely the slimes and ball,
 * and the goals, whatever they may be.
 * @author &{user}
 */
public abstract class Field extends GameObjectManager{
    
    public Field(Dimension gameDims){
	super(gameDims);
    }
    
    public void draw(Graphics2D g){
	//draw the field
	drawField(g);
	super.draw(g);
    }
    
    public void updateObject(long elapsedTime,GameObject o){
	//apply gravity if sprite
	if(o instanceof Sprite){
	    Sprite s = (Sprite)o;
	    
	    s.accelerate(getGravity());
	    
	    //check if sprite is touching floor
	    if(s.getY() + s.getHeight() >= getGround() && s.isFalling()){
		//stop the sprite from moving down
		s.stopY();
		
		//reset it to the ground
		s.position(s.getX(),getGround() - s.getHeight());
	    }
	}
    }
    
    protected abstract void drawField(Graphics2D g);
    protected abstract Vector2D getGravity();
    public abstract float getGround();
}
