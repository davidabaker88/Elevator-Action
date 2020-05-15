package main.java;

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
        

        hitbox.y += Math.ceil(velocityY);


    }
    private void jump(){

    }

}
