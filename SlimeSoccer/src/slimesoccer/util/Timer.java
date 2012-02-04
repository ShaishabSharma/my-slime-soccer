/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package slimesoccer.util;

/**
 *
 * @author Laptop2010
 */
public class Timer {
    private long interval;
    private long totaltime = 0;
    private boolean started = false;

    public Timer(long interval){
	this.interval = interval;
	totaltime = 0;
    }

    public Timer(){
	this(0);
    }

    public void setInterval(long interval){
	this.interval = interval;
    }
    
    public long getElapsed(){
	return totaltime;
    }
    
    public long getInterval(){
	return interval;
    }
    
    public long getTimeToNextTick(){
	return interval - totaltime;
    }
    
    public void update(long elapsedTime){
	if(!started)return;
	
	totaltime += elapsedTime;
    }

    public boolean hasTicked(){
	if (started && totaltime >= interval){
	    return true;
	}else{
	    return false;
	}

    }

    /**
     * Resets the timer. Should be called after each tick is sensed.
     */
    public void reset(){
	totaltime = 0;
    }

    /**
     * Start the timer. Should be called before the first tick is sensed.
     */
    public void start(){
	started = true;
    }

    /**
     * Stops and resets the timer.
     */
    public void stop(){
	started = false;
    }

    public boolean isStarted(){
	return started;
    }
}
