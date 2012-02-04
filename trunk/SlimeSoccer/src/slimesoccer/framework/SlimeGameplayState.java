/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slimesoccer.framework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import slimesoccer.entities.Ball;
import slimesoccer.input.GameAction;
import slimesoccer.input.InputManager;
import slimesoccer.entities.Field;
import slimesoccer.entities.Slime;
import slimesoccer.entities.SoccerField;

/**
 * Gameplay state in slime soccer.
 * @author &{user}
 */
public class SlimeGameplayState extends GameState{

    //game actions
    
    //player 1 - arrows
    GameAction p1MoveLeft;
    GameAction p1MoveRight;
    GameAction p1Jump;
    
    //player 2 - wasd
    GameAction p2MoveLeft;
    GameAction p2MoveRight;
    GameAction p2Jump;
    
    GameAction quit;
    
    //sprites
    private Field field;
    private Slime p1,p2;
    private Ball ball;
    
    
    public SlimeGameplayState(Dimension dims,InputManager input){
	super(dims,input);
	
	//create gameactions
	p1MoveLeft = new GameAction("p1 Move Left");
	p1MoveRight = new GameAction("p1 Move Right");
	p1Jump = new GameAction("p1 Jump");
	
	p2MoveLeft = new GameAction("p2 Move Left");
	p2MoveRight = new GameAction("p2 Move Right");
	p2Jump = new GameAction("p2 Jump");
	
	quit = new GameAction("quit",GameAction.DETECT_INITAL_PRESS_ONLY);
	
	//map keys
	input.mapToKey(p1MoveLeft, KeyEvent.VK_LEFT);
	input.mapToKey(p1MoveRight, KeyEvent.VK_RIGHT);
	input.mapToKey(p1Jump, KeyEvent.VK_UP);
	
	input.mapToKey(p2MoveLeft, KeyEvent.VK_A);
	input.mapToKey(p2MoveRight, KeyEvent.VK_D);
	input.mapToKey(p2Jump, KeyEvent.VK_W);
	
	input.mapToKey(quit, KeyEvent.VK_ESCAPE);
	
	//create field
	field = new SoccerField(dims);
	
	//create sprites
	
	//ball
	ball = new Ball(0f,0f,20f,Color.red);
	ball.position(gameDims.width/2f,field.getGround() - ball.getHeight());
	
	field.add(ball);
	
	//player 1
	p1 = new Slime(0f,0f,75f,Color.orange,ball,false);
	p1.position(gameDims.width * 3f/4f,field.getGround() - p1.getHeight());
	
	field.add(p1);
	
	//player 2
	p2 = new Slime(0f,0f,75f,Color.pink,ball,true);
	p2.position(gameDims.width /4f,field.getGround() - p2.getHeight());
	
	field.add(p2);
    }
    
    public void draw(Graphics2D g) {
	//draw field
	field.draw(g);
    }

    public void update(long elapsedTime) {
	//handle input
	input();
	
	//update sprites on field
	field.update(elapsedTime);
    }
    
    private void input(){
	if(!p1MoveLeft.isPressed() && !p1MoveRight.isPressed()){
	    p1.stopX();
	}else if(p1MoveLeft.isPressed()){
	    p1.move(false);
	}else if(p1MoveRight.isPressed()){
	    p1.move(true);
	}
	
	if(p1Jump.isPressed() && !p1.isFlying(field.getGround())){
	    p1.jump();
	}
	
	if(!p2MoveLeft.isPressed() && !p2MoveRight.isPressed()){
	    p2.stopX();
	}else if(p2MoveLeft.isPressed()){
	    p2.move(false);
	}else if(p2MoveRight.isPressed()){
	    p2.move(true);
	}
	
	if(p2Jump.isPressed() && !p2.isFlying(field.getGround())){
	    p2.jump();
	}
	
	if(quit.isPressed()){
	    finish();
	}
    }
    
    public boolean isDone(){
	return done;
    }
    
}
