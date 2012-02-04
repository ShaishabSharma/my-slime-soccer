/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slimesoccer.entities;

import java.awt.Graphics2D;
import slimesoccer.framework.GameObject;
import slimesoccer.util.Vector2D;

/**
 * Defines a dynamic animation.
 * @author Laptop2010
 */
public abstract class Sprite implements GameObject{
    
    protected Vector2D location;
    protected Vector2D velocity;
    
    protected float mass;
    
    private boolean collisionsenabled = true;
    private boolean physicsenabled = true;
    private boolean expired = false;
    private boolean visible = true;

    /**
     * Create a new sprite that is currently stationary.
     * @param a
     * @param x
     * @param y
     */
    public Sprite(float x,float y,float mass){
	location = new Vector2D(x,y);
        velocity = new Vector2D(0f,0f);
    }
    
    public float getMass(){
	return mass;
    }

    //advance the sprite
    public void update(long elapsedTime) {
        //move the sprite
        Vector2D.scratch.setTo(velocity);
        Vector2D.scratch.scale((float)elapsedTime);
	location.add(Vector2D.scratch);
    }

    /**
     * Draw the sprite.
     * @param g
     */
    public abstract void draw(Graphics2D g);
    
    public abstract float getWidth();
    public abstract float getHeight();
    
    public float getX() {
        return location.x;
    }

    public float getY() {
        return location.y;
    }
    
    public float centerX(){
	return location.x + getWidth()/2;
    }
    
    public float centerY(){
	return location.y + getHeight()/2;
    }

    public boolean isExpired() {
	return expired;
    }

    public void expire() {
	expired = true;
    }

    public boolean isVisible() {
	return visible;
    }

    public void setVisible(boolean b) {
	visible = b;
    }

    public boolean isPhysicsEnabled() {
	return physicsenabled;
    }

    public void setPhysicsEnabled(boolean physicsenabled) {
	this.physicsenabled = physicsenabled;
    }
    
    
    public boolean areCollisionsEnabled(){
	return collisionsenabled;
    }

    public void setCollisionsEnabled(boolean b){
	collisionsenabled = b;
    }
    
    public Vector2D getVelocity(){
        return velocity;
    }
    
    public Vector2D getLocation(){
        return location;
    }
    
    public void position(float x,float y){
	location.x = x;
	location.y = y;
    }
    
    public boolean isOffScreen(int swidth,int sheight){
	return (location.x < -getWidth() || location.y < -getHeight() || location.x > swidth || location.y > sheight);
    }
    
    public boolean isInsideBounds(float x,float y){
	return (x >= getX() && x <= getX() + getWidth() && y >= getY() && y <= getY() + getHeight());
    }
    
    public boolean isFlying(float ground){
	return getY() + getHeight() < ground;
    }
    
    public boolean isFalling(){
	return velocity.y > 0f;
    }
    
    public void stopX(){
	velocity.x = 0f;
    }
    
    public void stopY(){
	velocity.y = 0f;
    }
    
    public void accelerate(Vector2D v){
	velocity.add(v);
    }
    
    public void deccelerate(Vector2D v){
	velocity.subtract(v);
    }
    
    public abstract void collision(Sprite other);
}
