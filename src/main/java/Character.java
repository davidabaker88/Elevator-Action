package main.java;

import java.awt.*;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * Created by dbaker on 4/30/2020.
 */
public class Character extends Sprite {
	
    protected boolean hasHitBox;
    protected LinkedList<Bullet> bullets;
    
    
    
    public Character(){
        bullets = new LinkedList<>();
        hasHitBox = true;
        hitbox = new Rectangle(100,100,20,20);
    }
    
    public Character(String startAnimation) {
    	super(startAnimation);
    	bullets = new LinkedList<>();
        hasHitBox = true;
        hitbox = new Rectangle(100,100,getTexture().getImage().getWidth(null),getTexture().getImage().getHeight(null));
    }
    
    public Character(String startAnimation,int startAnimationPace) {
    	bullets = new LinkedList<>();
        hasHitBox = true;
        

        hitbox = new Rectangle(100,100,20,20);
    }
    
    
    
    
    
    
    protected void crouch(){

    }

    protected void openDoor(){

    }
    
    protected void move(){

    }
    
    protected void stand(){

    }
    
    protected void getOnEscalator(){

    }
    
    protected void shoot(){

    }
    
    public void draw(Graphics2D g2d){
        //g2d.drawImage(Image, xpos, ypos, );
        g2d.drawImage(getTexture().getImage(),hitbox.x,hitbox.y,Main.getInstance());
    }
}
