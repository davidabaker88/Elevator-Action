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

    public void Update(double delta){
    	//update animation
    	texture.nextFrame();
        //use the delta
        double gravity = 9.8 *delta;
        velocity+=gravity;

        //check for collision of ground


        hitbox.y += Math.ceil(velocity);


    }
    private void Jump(){

    }

}
