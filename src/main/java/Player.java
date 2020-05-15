package main.java;

/**
 * Created by dbaker on 4/30/2020.
 */
public class Player extends Character{
    private int lives;
    private int score;
    private double velocity;
    public Player(){
        super();
        lives = 3;
        score= 0;
        velocity = 0;
    }

    public void update(double delta){
    	//update animation for each frame
    	for (int i=0;i<Math.ceil(delta);i++) {
    		texture.nextFrame();
    	}
    	
        //use the delta
        double gravity = 9.8 *delta;
        velocity+=gravity;

        //check for collision of ground


        hitbox.y += Math.ceil(velocity);


    }
    private void jump(){

    }

}
