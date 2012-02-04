/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slimesoccer.entities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import slimesoccer.framework.GameObject;
import slimesoccer.util.Vector2D;

/**
 * Represents the default soccer field.
 * @author &{user}
 */
public class SoccerField extends Field{
    
    private Rectangle leftGoalArea,rightGoalArea;
    private float ground;
    
    //constants
    private static final Vector2D gravity = new Vector2D(0.0f,.03f);
    private static final Color groundColor = Color.green;

    public SoccerField(Dimension gameDims){
	super(gameDims);
	
	leftGoalArea = new Rectangle();
	rightGoalArea = new Rectangle();
	
	ground = gameDims.height * 3f/4f;
    }
    
    protected Vector2D getGravity(){
	return gravity;
    }
    
    public float getGround(){
	return ground;
    }
    
    protected void drawField(Graphics2D g) {
	//draw backdrop
	g.setColor(Color.blue);
	g.fillRect(0,0,gameDims.width,gameDims.height);
	
	//draw the ground
	g.setColor(groundColor);
	g.fillRect(0,(int)ground,gameDims.width,(int)(gameDims.height - ground));
	
	//draw the goals
	
    }

    public void updateObject(long elapsedTime,GameObject o){
	super.updateObject(elapsedTime, o);  //apply gravity & floor cd to the object
    }
    
}
