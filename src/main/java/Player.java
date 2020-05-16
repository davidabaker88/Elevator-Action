package main.java;

import java.awt.Rectangle;

/**
 * Created by dbaker on 4/30/2020.
 */
public class Player extends Character{
    private int lives;
    private int score;
    private double velocityY;
    private double velocityX;
    public Player(){
        super("playerWalkLeft");//starting texture added in super function
        addTexture("playerWalkRight");
        addTexture("playerStand");
        lives = 3;
        score= 0;
        velocityY = 0;
        velocityX = 0;
    }

    public void update(double delta){
    	//update animation for each frame
    	AnimatedTexture texture=getTexture();
    	for (int i=0;i<Math.ceil(delta);i++) {
    		texture.update(delta);
    	}
    	
        //use the delta
        double gravity = 9.8 *delta/Main.TARGET_FPS;//frames/(frames/second)=seconds therefore using this formula the player should accelerate at 9.8 m/s/s
        velocityY+=gravity;

        //check for collision of ground
        double movementY=0;
        double movementX=0;
        double dt=0;
        double interval=.001/velocityY;
        while (dt<=delta) {
        	dt+=interval;
        	movementY=velocityY*dt;
        	movementX=velocityX*dt;
        	if (hitbox.getMaxY()+movementY>=Main.getInstance().getFloor().hitbox.y) {
        		//stop moving
        		movementY=Math.floor(movementY);//don't move this pixel down
        		velocityY=0;
        		break;
        	}
        }
        
        hitbox.y += Math.ceil(movementY);
        if (velocityX>0) {
        	hitbox.x += Math.ceil(movementX);
        } else {
        	hitbox.x += Math.floor(movementX);
        }
        


    }
    private void jump(){

    }
    
    public void setXVelocity(double v) {
    	velocityX=v;
    }

}
