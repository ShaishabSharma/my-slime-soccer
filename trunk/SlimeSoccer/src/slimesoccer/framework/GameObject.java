/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slimesoccer.framework;

import java.awt.Graphics2D;

/**
 *
 * @author Laptop2010
 */
public interface GameObject {
    public void expire();
    public boolean isExpired();
    public void draw(Graphics2D g);
    public void update(long elapsedTime);
    public float getX();
    public float getY();
    public float getWidth();
    public float getHeight();
    public boolean isOffScreen(int swidth,int sheight);
}
