package slimesoccer.framework;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * This class provides a framework for a game that progresses
 * through several states throughout its lifetime.
 * @author &{user}
 */
public abstract class GameStateManager {

    protected GameState currentState;
    private boolean running = true;
    private long sleepTime = 2;
    private long previousRenderTime;
    private long elapsedTime;

    /**
     * This method should be the insertion point for the game thread.
     */
    public final void run() {
	
	
	    try {
	previousRenderTime = System.currentTimeMillis();

	while (running) {
	    //update game state
	    if (currentState != null) {
		if (!currentState.isDone()) {
		    //update current state
		    update();

		    //draw current state
		    draw();
		} else {
		    //get next state
		    currentState = getGameState();
		}
	    } else {
		//stop the game if no more states
		stop();
	    }

	    
	    
	}
	//sleep time
		Thread.sleep(sleepTime);
	    } catch (Exception e) {
	    }finally{
		cleanUp();
	    }
    }

    public final void update() {
	//measure the amount of time inbetween game updates and update the game accordingly
	elapsedTime = System.currentTimeMillis() - previousRenderTime;
	previousRenderTime = System.currentTimeMillis();

	currentState.update(elapsedTime);
    }

    public abstract GameState getGameState();
    
    public abstract void cleanUp();

    /*
     * Will be overridden to draw game state to screen
     */
    public abstract void draw();

    public void stop() {
	running = false;
    }
}
