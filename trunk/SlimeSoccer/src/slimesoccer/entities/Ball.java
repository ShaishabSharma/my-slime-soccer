/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slimesoccer.entities;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Represents the ball.
 * @author &{user}
 */
public class Ball extends Sprite{
    
    private float radius;    //radius of the ball	
    private Color color;     //color of the ball
    
    //ball constants
    private static final float ballDensity = 3.0f;  //ball density in grams/pixel
    
    //x and y represent ball's upper left coordinates
    public Ball(float x,float y,float radius,Color c){
	super(x,y,(float)Math.PI * radius * radius * ballDensity);
	this.color = c;
	this.radius = radius;
    }


    public void draw(Graphics2D g) {
	g.setColor(color);
	g.fillArc((int)getX(),(int)getY(), (int)getWidth(),(int)getHeight(),0,360);
    }

 
    public float getHeight() {
	return radius * 2;
    }

  
    public float getWidth() {
	return radius * 2;
    }
    
    public void collision(Sprite other){
    
    }
    
}
