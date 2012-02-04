/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slimesoccer.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import slimesoccer.framework.GameObject;
import slimesoccer.util.Vector2D;

/**
 * This class represents a slime in slime soccer.
 * @author &{user}
 */
public class Slime extends Sprite{
    
    private float radius;
    private Color color;
    private Eye eye;
    private boolean facingRight;
    
    //slime constants

    private static final float eyeElev = 30.0f;	    //degrees of elevation for eye
    private static final float eyeScale = 0.75f;    //eye distance from center proportional to radius
    private static final float eyeSize = 0.15f;     //eye size proportional to radius
    private static final float slimeDensity = 3.0f; //slime density in grams/pixel
    private static final float slimeSpeed = .3f;    //slime movement speed
    private static final float jumpAccel = -.7f;     //slime jump acceleration
    
    public Slime(float x,float y,float radius,Color c,GameObject eyeTarget,boolean facingRight){
	super(x,y,(float)Math.PI * radius * radius * slimeDensity / 2f); //slime mass is semicircle area times density
	
	this.radius = radius;
	this.color = c;
	
	//create the eye
	Vector2D eyePos = new Vector2D(0f,0f);
	eyePos.setPolar(radius * eyeScale, (facingRight)?(float)Math.toRadians(eyeElev):(float)Math.toRadians(180 - eyeElev));
	eye = new Eye(getX(),getY(),eyePos,eyeTarget,radius * eyeSize);
	
	//store whether the slime is facing right or left
	this.facingRight = facingRight;
    }
    
    public void collision(Sprite other) {
	
    }
    
    public void update(long elapsedTime){
	super.update(elapsedTime);
	
	//position the eye
	eye.positionEye(getX(),getY());
	eye.update(elapsedTime);
    }
    
    public void draw(Graphics2D g) {
	g.setColor(color);
	g.fillArc((int)getX(),(int)getY(),(int)getWidth(),(int)getHeight() * 2,0,180);
	
	eye.draw(g);
    }

    public float getHeight() {
	return radius;
    }

    public float getWidth() {
	return radius * 2;
    }
    
    //return the bottom point of the slime.
    public float getCenterY(){
	return getY() + getHeight();
    }
    
    public void move(boolean right){
	velocity.x = (right)?slimeSpeed:-slimeSpeed;
    }
    
    public void jump(){
	velocity.y = jumpAccel;
    }
    
    public boolean isFacingRight(){
	return facingRight;
    }
    
    
    
}
