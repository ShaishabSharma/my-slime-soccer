/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slimesoccer.ui;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import slimesoccer.util.Timer;

/**
 * This text output object fades away if
 * new text isn't printed in a certain amount of time.
 * @author &{user}
 */
public class FadingTextOutput extends TextOutput{

    private Timer fadeTimer;
    private long waitTime,fadeTime;
    private float alpha = 1f;
    
    private boolean waiting = true;
    
    public FadingTextOutput(int x, int y, Font font, int lineHeight, int maxlines,long waitTime,long fadeTime) {
	super(x, y, font, lineHeight, maxlines);
	this.waitTime = waitTime;
	this.fadeTime = fadeTime;
	
	fadeTimer = new Timer(waitTime);
	fadeTimer.start();
    }
    
    /**
     * Prints text to this output,
     * reseting the fading effect.
     * @param text 
     */
    public void print(String text){
	waiting = true;
	fadeTimer.setInterval(waitTime);
	fadeTimer.start();
	fadeTimer.reset();
        alpha = 1f;
	
	super.print(text);
    }
    
    public void update(long elapsedTime){
	//do not update if done
	if(!fadeTimer.isStarted())return;
	
	//update timer
	fadeTimer.update(elapsedTime);
	
	if(waiting){
	
	    //wait while text isn't being entered
	    if(fadeTimer.hasTicked()){
		waiting = false;
		
		//begin the fading effect
		fadeTimer.setInterval(fadeTime);
		fadeTimer.reset();
	    }
	}else{
	    
	    //calculate new alpha value
	    alpha = 1f - (float)fadeTimer.getElapsed() / (float)fadeTimer.getInterval();
            
            alpha = Math.min(alpha,1f);
            alpha = Math.max(alpha,0);
	    
	    if(fadeTimer.hasTicked()){
		fadeTimer.stop();
		fadeTimer.reset();
                
                //clear messages
                lines.clear();
	    }
	}
    }
    
    public void draw(Graphics2D g){
	//do not draw if done
	if(!fadeTimer.isStarted())return;
	else{
	    //set alpha value
	    Composite ac = g.getComposite();
	    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
	    
	    //draw
	    super.draw(g);
	    
	    //reset alpha
	    g.setComposite(ac);
	}
    }
    
}
