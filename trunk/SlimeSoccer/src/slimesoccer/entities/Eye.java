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
 * Represents a slime's eye, which can track a gameobject.
 * @author &{user}
 */
public class Eye extends Sprite{

    //eye vars
    private float radius;       //the radius of the eye
    private GameObject target;  //the eye's focusing target
    private Vector2D pos;	//offset from slime center
    
    //iris vars
    private Vector2D iris;
    private float irisRadius;  //radius of the eye's iris
    private float irisDist;    //distance of the iris from the center during focusing	 
    
    //eye constants
    private static final float eyeDensity = 3.0f;   //density of eye in grams/pixel
    private static final float irisScale = .5f;     //length of iris radius in proportion to eye radius 
    
    public Eye(float slimex,float slimey,Vector2D pos,GameObject target,float radius){
	//eye's x and y represent the center point of the circle
	super(slimex + pos.x, slimey + pos.y,(float)Math.PI * radius * radius * eyeDensity);
	
	//setup eye
	this.radius = radius;
	this.pos = pos;
	this.target = target;
	
	positionEye(slimex,slimey);
	
	//setup iris
	iris = new Vector2D(0f,0f);
	irisRadius = radius * irisScale;
	irisDist = irisRadius;
	positionIris();
    }
    
    public void update(long elapsedTime){
	//update position of iris
	positionIris();
    }
    
    public void draw(Graphics2D g) {
	//draw white of eye first
	g.setColor(Color.white);
	g.fillArc((int)(getX() - radius),(int)(getY() - radius),(int)getWidth(),(int)getHeight(),0,360);
	
	//draw the iris
	g.setColor(Color.black);
	g.fillArc((int)(getX() + iris.x),(int)(getY() + iris.y),(int)irisRadius * 2,(int)irisRadius * 2,0,360);
    }

   
    public float getHeight() {
	return radius * 2;
    }

 
    public float getWidth() {
	return radius * 2;
    }
    
    public final void positionEye(float slimex,float slimey){
	//update position of center of eye - for target finding
	pos.x += slimex;
	pos.y += slimey;
    }
    
    /**
     * Calculate positioning of iris based on
     * target.
     */
    private void positionIris(){
	if(target != null){
	    //find vector to target
	    Vector2D scratch = Vector2D.scratch;
	    scratch.setTo(target.getX(), target.getY());

	    //subtract the eye position
	    scratch.subtract(location);

	    //normalize
	    scratch.normalize();

	    //scale
	    scratch.scale(irisDist);

	    //postion iris
	    iris.setTo(getX() + scratch.x,getY() + scratch.y);
	}
    }

   
    public void collision(Sprite other) {
	// eyes dont collide
    }
}
